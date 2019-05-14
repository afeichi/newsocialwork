package paramDemo;

import com.opensymphony.xwork2.ActionSupport;
//ok
public class Demo9Action extends ActionSupport {
	private User user;

	public String execute() throws Exception {
		System.out.println(user);
		return SUCCESS;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
