package ActionDemo;

import com.opensymphony.xwork2.ActionSupport;
public class Demo4Action  extends ActionSupport{

	public String execute() throws Exception {
		System.out.println("open 4");
		return SUCCESS;
	}
	
}
