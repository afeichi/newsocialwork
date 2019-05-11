
package zkSocialNetworkProject.shetuan.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import net.sf.json.JSONArray;
import zkSocialNetworkProject.domain.CommunityAD;
import zkSocialNetworkProject.shetuan.domain.CommPage;
import zkSocialNetworkProject.shetuan.service.CommMyAdService;
import zkSocialNetworkProject.shetuan.service.CommPageService;
import zkSocialNetworkProject.shetuan.service.serviceImp.CommMyAdServiceImp;
import zkSocialNetworkProject.shetuan.service.serviceImp.CommPageServiceImp;
import zkSocialNetworkProject.utils.BaseServlet;
import zkSocialNetworkProject.utils.UploadUtils;

/**
 * Servlet implementation class CommPageServlet
 */
@WebServlet("/CommPageServlet")
public class CommPageServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
	CommPageService commPageService = new CommPageServiceImp();
	CommMyAdService commMyAdService = new CommMyAdServiceImp();
	
	//社团主页的显示
	public String getCommPage(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String communityId = request.getParameter("commId");
		System.out.print(request.getAttribute("commId"));
		System.out.print(request.getParameter("commId"));
		CommPage commPage = commPageService.getCommPage(communityId);
		List<CommunityAD> commAD = commMyAdService.getCommADByUid(communityId);
		Object[] result = {commPage,commAD};
		String jsonStr = JSONArray.fromObject(result).toString();
		//响应到客户端
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().println(jsonStr);
		//转发到首页
		return null;
	}
	
	//社团主页的添加（完成社团注册后进行）
	public String addCommPage(HttpServletRequest request, HttpServletResponse response) throws Exception{
		//map存储表单中数据
		Map<String,String> map=new HashMap<String,String>();
		//
		CommPage commPage = new CommPage();
		//携带表单中的数据向servcie,dao
		try {
			//利用req.getInputStream();获取到请求体中全部数据,进行拆分和封装
			DiskFileItemFactory fac=new DiskFileItemFactory();
			ServletFileUpload upload=new ServletFileUpload(fac);
			List<FileItem> list;
				list = upload.parseRequest(request);
			//遍历集合
			for (FileItem item : list) {
				if(item.isFormField()){
					//如果当前的FileItem对象是普通项
					//将普通项上name属性的值作为键,将获取到的内容作为值,放入MAP中
						map.put(item.getFieldName(), item.getString("utf-8"));
				}else{
					//如果当前的FileItem对象是上传项
					//获取到原始的文件名称
					String oldFileName=item.getName();
					//获取到要保存文件的名称   1222.doc  
					String newFileName=UploadUtils.getUUIDName(oldFileName);
					//通过FileItem获取到输入流对象,通过输入流可以获取到图片二进制数据
					InputStream is=item.getInputStream();
					
					
					//获取到当前项目放图片的真实路径
					String realPath=getServletContext().getRealPath("");
					
					
					String dir=UploadUtils.getDir(newFileName); // /f/e/d/c/4/9/8/4
					String path=realPath+dir; //D:\tomcat\tomcat71_sz07\webapps\store_v5\products\3/f/e/d/c/4/9/8/4
					//内存中声明一个目录
					File newDir=new File(path);
					if(!newDir.exists()){
						newDir.mkdirs();
					}
					//在服务端创建一个空文件(后缀必须和上传到服务端的文件名后缀一致)
					File finalFile=new File(newDir,newFileName);
					if(!finalFile.exists()){
						finalFile.createNewFile();
					}
					//建立和空文件对应的输出流
					OutputStream os=new FileOutputStream(finalFile);
					//将输入流中的数据刷到输出流中
					IOUtils.copy(is, os);
					//释放资源
					IOUtils.closeQuietly(is);
					IOUtils.closeQuietly(os);
					//向map中存入图片的路径
					if(item.getFieldName().equals("head")) {
						map.put("head","/products/3/"+dir+"/"+newFileName);
					}else{
						map.put("backPic","/products/3/"+dir+"/"+newFileName);
					}
					
				}
			}
			
			//利用BeanUtils将MAP中的数据填充到CommunityAD对象上
			//获取当前登录用户id
			HttpSession session = request.getSession();
			String communityId = (String) session.getAttribute("communityId");
			BeanUtils.populate(commPage, map);
			commPage.setCommunityId(communityId);
			//调用servcie_dao将commAlbum上携带的数据存入数据仓库,重定向到社团首页
			commPageService.addCommPage(commPage);
			response.sendRedirect("/store_v5/AdminProductServlet?method=findAllProductsWithPage&num=1");
			
			
		} catch (Exception e) {
			e.printStackTrace();
			String errorMsg = "发生错误，请刷新页面";
			request.setAttribute("errorMsg", errorMsg);
			return "";
		}
		return null;
	}
	
	//社团主页信息的修改（背景图，头像等）
	public String updateCommPage(HttpServletRequest request, HttpServletResponse response) throws Exception{
		//map存储表单中数据
				Map<String,String> map=new HashMap<String,String>();
				//
				CommPage commPage = new CommPage();
				//携带表单中的数据向servcie,dao
				try {
					//利用req.getInputStream();获取到请求体中全部数据,进行拆分和封装
					DiskFileItemFactory fac=new DiskFileItemFactory();
					ServletFileUpload upload=new ServletFileUpload(fac);
					List<FileItem> list;
						list = upload.parseRequest(request);
					//遍历集合
					for (FileItem item : list) {
						if(item.isFormField()){
							//如果当前的FileItem对象是普通项
							//将普通项上name属性的值作为键,将获取到的内容作为值,放入MAP中
								map.put(item.getFieldName(), item.getString("utf-8"));
						}else{
							//如果当前的FileItem对象是上传项
							//获取到原始的文件名称
							String oldFileName=item.getName();
							//获取到要保存文件的名称   1222.doc  
							String newFileName=UploadUtils.getUUIDName(oldFileName);
							//通过FileItem获取到输入流对象,通过输入流可以获取到图片二进制数据
							InputStream is=item.getInputStream();
							
							
							//获取到当前项目放图片的真实路径
							String realPath=getServletContext().getRealPath("");
							
							
							String dir=UploadUtils.getDir(newFileName); // /f/e/d/c/4/9/8/4
							String path=realPath+dir; //D:\tomcat\tomcat71_sz07\webapps\store_v5\products\3/f/e/d/c/4/9/8/4
							//内存中声明一个目录
							File newDir=new File(path);
							if(!newDir.exists()){
								newDir.mkdirs();
							}
							//在服务端创建一个空文件(后缀必须和上传到服务端的文件名后缀一致)
							File finalFile=new File(newDir,newFileName);
							if(!finalFile.exists()){
								finalFile.createNewFile();
							}
							//建立和空文件对应的输出流
							OutputStream os=new FileOutputStream(finalFile);
							//将输入流中的数据刷到输出流中
							IOUtils.copy(is, os);
							//释放资源
							IOUtils.closeQuietly(is);
							IOUtils.closeQuietly(os);
							//向map中存入图片的路径
							if(item.getFieldName().equals("head")) {
								map.put("head","/products/3/"+dir+"/"+newFileName);
							}else{
								map.put("backPic","/products/3/"+dir+"/"+newFileName);
							}
							
						}
					}
					
					//利用BeanUtils将MAP中的数据填充到CommunityAD对象上
					//获取当前登录用户id
					HttpSession session = request.getSession();
					String communityId = (String) session.getAttribute("communityId");
					BeanUtils.populate(commPage, map);
					commPage.setCommunityId(communityId);
					//调用servcie_dao将commAlbum上携带的数据存入数据仓库,重定向到社团首页
					commPageService.updateCommPage(commPage);
					response.sendRedirect("/store_v5/AdminProductServlet?method=findAllProductsWithPage&num=1");
					
					
				} catch (Exception e) {
					e.printStackTrace();
					String errorMsg = "发生错误，请刷新页面";
					request.setAttribute("errorMsg", errorMsg);
					return "";
				}
				return null;
		
	}
}
