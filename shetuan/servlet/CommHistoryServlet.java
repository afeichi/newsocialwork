package zkSocialNetworkProject.shetuan.servlet;


import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import zkSocialNetworkProject.shetuan.domain.CommAlbum;
import zkSocialNetworkProject.shetuan.domain.CommHistory;
import zkSocialNetworkProject.shetuan.domain.CommHistoryPic;
import zkSocialNetworkProject.shetuan.service.CommHistoryService;
import zkSocialNetworkProject.shetuan.service.serviceImp.CommHistoryServiceImp;
import zkSocialNetworkProject.utils.BaseServlet;
import zkSocialNetworkProject.utils.UUIDUtils;
import zkSocialNetworkProject.utils.UploadUtils;

/**
 * Servlet implementation class CommHistoryServlet
 */
@WebServlet("/CommHistoryServlet")
public class CommHistoryServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	CommHistoryService commHistoryService = new CommHistoryServiceImp();
	
	//添加往届活动
	public String addHistory(HttpServletRequest request, HttpServletResponse response) throws Exception{
		//map存储表单中数据
		Map<String,String> map=new HashMap<String,String>();
		//
		List<String> commHistoryPic = new ArrayList<String>();
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
					//向commPic中存入图片的路径
					commHistoryPic.add("/products/3/"+dir+"/"+newFileName);
				}
			}
			
			//利用BeanUtils将MAP中的数据填充到CommunityAD对象上
			CommHistory commHistory = new CommHistory();
			commHistory.setHistoryId(UUIDUtils.getUUID());
			commHistory.setPicList(commHistoryPic);
			//获取当前登录用户id
			HttpSession session = request.getSession();
			String communityId = (String) session.getAttribute("communityId");
			BeanUtils.populate(commHistory, map);
			commHistory.setCommunityId(communityId);
			//调用servcie_dao将commAlbum上携带的数据存入数据仓库,重定向到相册首页
			commHistoryService.addHistory(commHistory);
			response.sendRedirect("/store_v5/AdminProductServlet?method=findAllProductsWithPage&num=1");
			
			
		} catch (RuntimeException e) {
			e.printStackTrace();
			String errorMsg = "发生错误，请刷新页面";
			request.setAttribute("errorMsg", errorMsg);
			return "";
		}
		return null;
	}
	
	//展示往届活动列表
	public String getCommHistory(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String communityId = request.getParameter("commId");
		List<CommHistory> commHistoryList = commHistoryService.getCommHistory(communityId);
		String jsonData = JSONArray.fromObject(commHistoryList).toString();
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().write(jsonData);
		return null;
	}
	
	//删除往届活动
	public String deleteHistory(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String historyId = request.getParameter("historyId");
		commHistoryService.deleteHistory(historyId);
		return null;
	}
	
	//进入活动详情页
	public String getCommHistoryDetail(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String historyId = request.getParameter("historyId");
		CommHistory commHistoryDetail = commHistoryService.getCommHistoryDetail(historyId);
		String jsonData = JSONArray.fromObject(commHistoryDetail).toString();
		response.getWriter().write(jsonData);
		return null;
	}
}
