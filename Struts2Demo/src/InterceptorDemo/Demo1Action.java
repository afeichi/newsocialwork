package InterceptorDemo;

import com.opensymphony.xwork2.ActionSupport;

public class Demo1Action extends ActionSupport{
	public String add() {
		System.out.println("Demo1Action_add");
		return SUCCESS;
	}
	public String delete() {
		System.out.println("Demo1Action_delete");
		return SUCCESS;
	}
	public String update() {
		System.out.println("Demo1Action_update");
		return SUCCESS;
	}
	public String find() {
		System.out.println("Demo1Action_find");
		return SUCCESS;
	}
}
