package zkSocialNetworkProject.shetuan.servlet;

import java.io.File;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import net.sf.json.JSONArray;
import zkSocialNetworkProject.shetuan.domain.Introduction;
import zkSocialNetworkProject.shetuan.service.CommIntroductService;
import zkSocialNetworkProject.shetuan.service.serviceImp.CommIntroductServiceImp;
import zkSocialNetworkProject.utils.BaseServlet;
import zkSocialNetworkProject.utils.UploadUtils;

/**
 * Servlet implementation class CommIntroductServlet
 */
@WebServlet("/CommIntroductServlet")
public class CommIntroductServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	CommIntroductService commIntroductService = new CommIntroductServiceImp();

	// 社团简介的显示
	public String showCommIntroduction(HttpServletRequest request, HttpServletResponse response) {
		// 获取社团id
		String commId = request.getParameter("commId");

		Introduction commIntroduct = commIntroductService.getCommIntroduct(commId);
		String commIntroductStr = JSONArray.fromObject(commIntroduct).toString();
		try {
			response.setContentType("application/json;charset=utf-8");
			response.getWriter().write(commIntroductStr);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// 社团简介的添加或修改
	public String addCommIntroduction(HttpServletRequest request, HttpServletResponse response) {
		// 存储表单中数据
		Map<String, String> map = new HashMap<String, String>();
		Introduction introduct = new Introduction();
		try {
			BeanUtils.populate(introduct, map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		Introduction introductTemp = commIntroductService.isIntroExist(introduct.getCommId());
		if (introductTemp != null) {
			commIntroductService.updateCommIntroduct(introduct);
		} else {
			// 携带表单中的数据向servcie,dao
			introduct = new Introduction();
				commIntroductService.addCommIntroduct(introduct);
		}
		return null;
	}

	// 社团简介的修改（弃用）
	public String updateCommIntroduction(HttpServletRequest request, HttpServletResponse response) {
		// 存储表单中数据
		Map<String, String> map = new HashMap<String, String>();
		// 携带表单中的数据向servcie,dao
		Introduction introduct = new Introduction();
		try {
			BeanUtils.populate(introduct, map);
			commIntroductService.updateCommIntroduct(introduct);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
