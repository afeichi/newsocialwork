package OgnlDemo;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class Demo2Action extends ActionSupport implements ModelDriven<User>{
	
		private User u=new User();
		




	@Override
	public String execute() throws Exception {
		System.out.println(u);
		return SUCCESS;
	}
	@Override
	public User getModel() {
		return null;
	}
}
