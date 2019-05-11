package zkSocialNetworkProject.shetuan.servlet;

import zkSocialNetworkProject.domain.shetuanUser;
import zkSocialNetworkProject.shetuan.service.UserService;
import zkSocialNetworkProject.utils.BaseServlet;
import zkSocialNetworkProject.utils.MailUtils;
import zkSocialNetworkProject.utils.UUIDUtils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;


@WebServlet("/shetuanregister.do")
public class shetuanregister extends BaseServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		//获得表单数据
		Map<String,String[]> properties=request.getParameterMap();
		shetuanUser user = new shetuanUser();
		try {
			BeanUtils.populate(user, properties);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
				user.setUserid(UUIDUtils.getUUID());
				user.setState(0);      
				String activeCode = UUIDUtils.getUUID();
				user.setCode(activeCode);
		//将user传递给service层
		UserService service = new UserService();
		boolean isRegisterSuccess = service.regist(user);
		
		  //是否注册成功 
		if(isRegisterSuccess){ //发送激活邮件
			String emailMsg ="恭喜您注册成功，请点击下面的连接进行激活账户" +
		  "<a href='http://localhost:8080/zkSocialNetworkProject/active?activeCode="+activeCode+"'>"
		  + "http://localhost:8080/zkSocialNetworkProject/active?activeCode="+activeCode+"</a>"; 
			try
		  { 
				MailUtils.sendMail(user.getEmail(), emailMsg);
				} catch
		  (MessagingException  e) 
			{
					e.printStackTrace(); 
					}

		  //跳转到注册成功页面
		  response.sendRedirect(request.getContextPath()+"/registerSuccess.jsp");
		  }
		else{ //跳转到失败的提示页面
		  response.sendRedirect(request.getContextPath()+"/registerFail.jsp"); }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}