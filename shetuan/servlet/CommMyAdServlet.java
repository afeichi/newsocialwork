package zkSocialNetworkProject.shetuan.servlet;


import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import zkSocialNetworkProject.domain.CommunityAD;
import zkSocialNetworkProject.shetuan.service.CommMyAdService;
import zkSocialNetworkProject.shetuan.service.serviceImp.CommMyAdServiceImp;
import zkSocialNetworkProject.utils.BaseServlet;

/**
 * Servlet implementation class CommMyAdServlet
 */
@WebServlet("/CommMyAdServlet")
public class CommMyAdServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
     
	CommMyAdService commMyAdService = new CommMyAdServiceImp();
	
	
/*	社团活动宣传的添加
	public String addCommAD(HttpServletRequest request, HttpServletResponse response) throws Exception{
		CommunityServlet中已实现
	}
*/
	
	//社团活动宣传的删除
	public String deleteCommAD(HttpServletRequest request, HttpServletResponse response) throws Exception{
		//获取此社团宣传的id
		String commId = (String) request.getAttribute("commId");
		//执行Service层的删除功能
		commMyAdService.deleteCommAD(commId);
		//重定向到社团活动页
		response.sendRedirect("");
		return null;
	}
	
	//社团活动宣传的显示
	public String getCommAD(HttpServletRequest request, HttpServletResponse response) throws Exception{
		//获取社团的id
		String uid = (String) request.getSession().getAttribute("uid");
		//获取已发布过的活动的信息
		List<CommunityAD> commAD = commMyAdService.getCommADByUid(uid);
		String jsonData = JSONArray.fromObject(commAD).toString();
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().write(jsonData);
		return null;
	}
}
