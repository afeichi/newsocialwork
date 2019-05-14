package InterceptorDemo;

import com.opensymphony.xwork2.ActionSupport;

public class Demo3Action extends ActionSupport{
	private String name;

	@Override
	public String execute() throws Exception {
		System.out.println(name);
		this.addActionError("YOU ARE WRONG");
		
		return SUCCESS;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
