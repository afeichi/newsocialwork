package ApiDemo;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
//测试成功
public class Demo5Action extends ActionSupport{
	//在action中获得原声ServletAPI
	public String execute() throws Exception {
		//request->map
		Map<String, Object> requestScope = (Map<String, Object>) ActionContext.getContext().get("request");
		ActionContext.getContext().put("name", "requestTom");
		
		//session->map
		Map<String,Object> sessionScope=ActionContext.getContext().getSession();
		sessionScope.put("name", "sessionjerry");
		
		//application->map
		Map<String,Object> applicationScope=ActionContext.getContext().getApplication();
		applicationScope.put("name", "applicationmarry");
		return SUCCESS;
	}

}
