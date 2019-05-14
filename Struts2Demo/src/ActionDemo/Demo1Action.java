package ActionDemo;

import com.opensymphony.xwork2.ActionSupport;
//³É¹¦
public class Demo1Action  extends ActionSupport{

	public String execute() throws Exception {
		System.out.println("open ActionSupport");
		return SUCCESS;
	}
	
}
