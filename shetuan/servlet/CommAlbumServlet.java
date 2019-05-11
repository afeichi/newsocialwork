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
import zkSocialNetworkProject.shetuan.service.CommAlbumService;
import zkSocialNetworkProject.shetuan.service.serviceImp.CommAlbumServiceImp;
import zkSocialNetworkProject.utils.BaseServlet;
import zkSocialNetworkProject.utils.UUIDUtils;
import zkSocialNetworkProject.utils.UploadUtils;

/**
 * Servlet implementation class CommAlbumServlet
 */
@WebServlet("/CommAlbumServlet")
public class CommAlbumServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	CommAlbumService commAlbumService = new CommAlbumServiceImp();
	
	
	//添加新的相片
	public String addCommAlbum(HttpServletRequest request, HttpServletResponse response) throws Exception{
		//map存储表单中数据
		Map<String,String> map=new HashMap<String,String>();
		String fileName = null;
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
					fileName=item.getName();
					//通过FileItem获取到输入流对象,通过输入流可以获取到图片二进制数据
					InputStream is=item.getInputStream();
					
					// 获取到当前项目放图片的真实路径
					String dir = "E:\\MyData\\活动、项目、比赛资料\\微信小程序\\F4\\pages\\images";
					
					//在服务端创建一个空文件(后缀必须和上传到服务端的文件名后缀一致)
					File finalFile=new File(dir,fileName);
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
				}
			}
			
			//利用BeanUtils将MAP中的数据填充到CommunityAD对象上
			CommAlbum commAlbum = new CommAlbum();
			BeanUtils.populate(commAlbum, map);
			commAlbum.setCphoto("/pages/images/"+fileName);
			//设置代理主键
			String pid = UUIDUtils.getUUID();
			commAlbum.setPid(pid);
			//设置上传时间
			SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
			Date date = new Date();
			String dateStr = sd.format(date);
			commAlbum.setDate(dateStr);
			//携带表单中的数据向servcie,dao传送
			commAlbumService.addCommAlbum(commAlbum);
			return null;
		} catch (RuntimeException e) {
			e.printStackTrace();
			String errorMsg = "发生错误，请刷新页面";
			request.setAttribute("errorMsg", errorMsg);
			return null;
		}
	}
	
	//显示相片列表
	public String getCommAlbum(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String uid = request.getParameter("commId");
		List<CommAlbum> commAlbum = commAlbumService.getCommAlbum(uid);
		String jsonStr = JSONArray.fromObject(commAlbum).toString();
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().write(jsonStr);
		return null;
	}
	
	//删除某日相片集(整栏删除)
	public String deleteCommAlbum(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String cid = request.getParameter("cid");
		commAlbumService.deleteCommAlbum(cid);
		//重定向到相片列表
		response.sendRedirect("");
		return null;
	}
	
	//删除单独一张照片
	public String deleteOnePic(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String cphoto = request.getParameter("cphoto");
		commAlbumService.deleteOnePhoto(cphoto);
		//重定向到相片列表
		return null;
	}
	
}
