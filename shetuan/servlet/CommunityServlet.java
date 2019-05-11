
package zkSocialNetworkProject.shetuan.servlet;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
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
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import net.sf.json.JSONArray;
import zkSocialNetworkProject.domain.CommAdBox;
import zkSocialNetworkProject.domain.CommAdPic;
import zkSocialNetworkProject.domain.CommunityAD;
import zkSocialNetworkProject.shetuan.service.CommunityService;
import zkSocialNetworkProject.shetuan.service.serviceImp.CommunityServiceImp;
import zkSocialNetworkProject.utils.BaseServlet;
import zkSocialNetworkProject.utils.UploadUtils;

/**
 * 社团首页宣传板块 Servlet implementation class CommunityServlet
 */
@WebServlet("/CommunityServlet")
public class CommunityServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	CommunityService communityService = new CommunityServiceImp();

	// 社团首页
	public String getComAD(HttpServletRequest request, HttpServletResponse response) {
		try {
			// 身份判断 根据用户id得到用户访问权限
			/*
			 * 待交接完成
			 */

			// 根据访问权限给予是否能够发布宣传的功能
			/*
			 * if() { request.setAttribute("is_", 1); }else { request.setAttribute("is_",
			 * 0); }
			 */

			// 最新三个社团活动宣传（轮播图）
			List<CommAdBox> newAdList = communityService.getNewCommAD();
			// 剩余社团活动
			List<CommAdBox> AdList = communityService.getCommAD();

			request.setAttribute("newAdList", newAdList);
			request.setAttribute("AdList", AdList);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			String errorMsg = "访问出现问题，请刷新";
			request.setAttribute("errorMsg", errorMsg);
			// 访问错误提示画面
			return "";
		}
		// 转发到社团首页
		return null;
	}

	public String publishComADUI(HttpServletRequest request, HttpServletResponse response) {
		// 转发到发布页面
		return null;
	}

	// 宣传发布第一次访问存基本信息
	public String publishComAD(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// map存储表单中数据
		Map<String, String[]> map = request.getParameterMap();
		
		// 利用BeanUtils将MAP中的数据填充到CommunityAD对象上
		CommunityAD commAD = new CommunityAD();
		BeanUtils.populate(commAD, map);
		commAD.setCommPublishDate(new Date());
		// 调用servcie_dao将commAD上携带的数据存入数据仓库
		communityService.publishCommunityAD(commAD);
		return null;
	}

	// 宣传发布第二次访问存图片路径
	public String publishCommADPic(HttpServletRequest request, HttpServletResponse response) {
		CommAdPic commAdPic = new CommAdPic();
		try {
			// 利用req.getInputStream();获取到请求体中全部数据,进行拆分和封装
			DiskFileItemFactory fac = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(fac);
			List<FileItem> list;
			list = upload.parseRequest(request);
			// 遍历集合
			for (FileItem item : list) {
				if (item.isFormField()) {
					commAdPic.setCommId(item.getString("utf-8"));
				} else {
					// 如果当前的FileItem对象是上传项
					// 获取到原始的文件名称
					String fileName = item.getName();
					// 通过FileItem获取到输入流对象,通过输入流可以获取到图片二进制数据
					InputStream is = item.getInputStream();

					// 获取到当前项目放图片的真实路径
					String dir = "E:\\MyData\\活动、项目、比赛资料\\微信小程序\\F4\\pages\\images";

					// 在服务端创建一个空文件(后缀必须和上传到服务端的文件名后缀一致)
					File finalFile = new File(dir, fileName);
					if (!finalFile.exists()) {
						finalFile.createNewFile();
					}
					// 建立和空文件对应的输出流
					OutputStream os = new FileOutputStream(finalFile);
					// 将输入流中的数据刷到输出流中
					IOUtils.copy(is, os);
					// 释放资源
					IOUtils.closeQuietly(is);
					IOUtils.closeQuietly(os);
					commAdPic.setCommPic("/pages/images/"+fileName);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		// 调用servcie_dao将commAD上携带的数据存入数据仓库
		communityService.publishCommADPic(commAdPic);
		return null;
	}

	// 宣传删除
	public String DeleteComAD(HttpServletRequest request, HttpServletResponse response) {
		try {
			// 获取宣传id
			String commId = request.getParameter("commId");
			// 执行删除功能
			communityService.deleteComAD(commId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			String errorMsg = "访问出现问题，请刷新";
			request.setAttribute("errorMsg", errorMsg);
			// 访问错误提示画面
			return "";
		}
		// 重定向到社团首页
		return null;
	}

	// 活动详情
	public String showComADDetails(HttpServletRequest request, HttpServletResponse response) {
		try {
			// 获取宣传id
			String commId = (String) request.getAttribute("commId");
			String uid = (String) request.getAttribute("uid");
			CommunityAD commAdCon = communityService.showCommAdCon(commId, uid);
			String jsonData = JSONArray.fromObject(commAdCon).toString();
			response.setContentType("application/json;charset=utf-8");
			response.getWriter().print(jsonData);
			return null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			String errorMsg = "访问出现问题，请刷新";
			request.setAttribute("errorMsg", errorMsg);
			// 访问错误提示画面
			return "";
		}
	}

	// 测试数据
	public String test(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, String> map = new HashMap<>();
		DiskFileItemFactory fac = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(fac);
		List<FileItem> list;
		list = upload.parseRequest(request);
		// 遍历集合
		for (FileItem item : list) {
			if (item.isFormField()) {
				// 如果当前的FileItem对象是普通项
				// 将普通项上name属性的值作为键,将获取到的内容作为值,放入MAP中
				map.put(item.getFieldName(), item.getString("utf-8"));
				System.out.print(item.getString());
			} else {
				// 如果当前的FileItem对象是上传项
				// 获取到原始的文件名称
				String fileName = item.getName();
				String realPath = "E:\\MyData\\活动、项目、比赛资料\\微信小程序\\F4\\pages\\images";
				// 在服务端创建一个空文件(后缀必须和上传到服务端的文件名后缀一致)
				File finalFile = new File(realPath, fileName);
				if (!finalFile.exists()) {
					finalFile.createNewFile();
				}
				InputStream is = item.getInputStream();
				// 建立和空文件对应的输出流
				OutputStream os = new FileOutputStream(finalFile);
				// 将输入流中的数据刷到输出流中
				IOUtils.copy(is, os);
				// 释放资源
				IOUtils.closeQuietly(is);
				IOUtils.closeQuietly(os);
			}
		}
		return null;
	}
}
