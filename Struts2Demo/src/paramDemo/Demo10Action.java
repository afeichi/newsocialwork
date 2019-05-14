package paramDemo;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
//³É¹¦
public class Demo10Action extends ActionSupport implements ModelDriven<User>{
	private User user=new User();

	public String execute() throws Exception {
		System.out.println(user);
		return SUCCESS;
	}
	
	public User getModel() {
		return user;
	}


	
}
