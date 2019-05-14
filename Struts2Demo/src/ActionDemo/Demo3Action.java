package ActionDemo;

import com.opensymphony.xwork2.ActionSupport;
public class Demo3Action  extends ActionSupport{

	public String execute() throws Exception {
		System.out.println("open ActionSupport33333");
		return SUCCESS;
	}
	
}
