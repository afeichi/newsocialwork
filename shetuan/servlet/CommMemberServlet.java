package zkSocialNetworkProject.shetuan.servlet;



import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import net.sf.json.JSONArray;
import zkSocialNetworkProject.shetuan.domain.CommMemberMsg;
import zkSocialNetworkProject.shetuan.domain.Commin;
import zkSocialNetworkProject.shetuan.domain.Student;
import zkSocialNetworkProject.shetuan.service.CommMemberService;
import zkSocialNetworkProject.shetuan.service.serviceImp.CommMemberServiceImp;
import zkSocialNetworkProject.utils.BaseServlet;

/**
 * Servlet implementation class CommMemberServlet
 */
@WebServlet("/CommMemberServlet")
public class CommMemberServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	CommMemberService commMemberService = new CommMemberServiceImp();
	
	//社团成员列表显示
	public String getCommMember(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String commId = request.getParameter("commId");
		List<CommMemberMsg> list = commMemberService.getCommMember(commId);
		String jsonData = JSONArray.fromObject(list).toString();
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().write(jsonData);
		return null;
	}
	
	protected String search(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		
		//查询学生信息
		//收集表单搜索数据
		String email =request.getParameter("email");
		//将实体传递给service层
		List<Student> studentList=null;
		studentList=commMemberService.findStudentList(email);
		//回显
		String jsonData = JSONArray.fromObject(studentList).toString();
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().write(jsonData);
		return null;
}
	
	//主动揽入为成员
	public String addInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		Commin commin=new Commin();
		//获取表单数据
		Map<String,String[]> properties=request.getParameterMap();
		//对表单信息进行封装
		BeanUtils.populate(commin, properties);
		boolean addInfo=commMemberService.addInfo(commin);
		if(addInfo)
		{
			return null;
		}	
		return null;
	}
	
	//修改成员的成员信息
	public String identity(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		//获取需要修改成员的id
		String identityId=(String) request.getAttribute("memberstatus");
		//执行修改功能
		commMemberService.idntity(identityId);
		return null;
	}
	
	//查询所有成员
		public String QuerymemberInfo(HttpServletRequest request,HttpServletResponse response) throws Exception {
			List<Commin> QueryallInfo =commMemberService.QuerymemberInfo();
			if(QueryallInfo!=null) {
				String jsonData = JSONArray.fromObject(QueryallInfo).toString();
				response.setContentType("application/json;charset=utf-8");
				response.getWriter().write(jsonData);
			}else {
//				request.setAttribute("nomember", "没有成员信息");
//				return null;
			}
			return null;
		}
		
		/*
		 * 通过部门查询成员信息
		 */	
		public String QuerymemberInfoBydep(HttpServletRequest request,HttpServletResponse response) throws IOException {
			//获取成员的部门
			String deparment=request.getParameter("deparment");
			List<Commin> depamember=commMemberService.QuerymemberInfoBydep(deparment);
			if(depamember!=null) {
				String jsonData = JSONArray.fromObject(depamember).toString();
				response.setContentType("application/json;charset=utf-8");
				response.getWriter().write(jsonData);
			}
			return null;
		}
		/*
		 * 删除成员信息
		 */
		public String deletememberInfo(HttpServletRequest request,HttpServletResponse response) {
			//获取成员的要删除的成员的id
			String sid=request.getParameter("sid");
			boolean deleteInfo=commMemberService.deletememberInfo(sid);
			return null;
		}
		
		//测试
		public String test(HttpServletRequest request,HttpServletResponse response) throws Exception
		{	
			Map<String, String[]> map = request.getParameterMap();
			return null;
		}
}
