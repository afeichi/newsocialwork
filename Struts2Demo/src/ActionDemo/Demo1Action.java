package ActionDemo;

import com.opensymphony.xwork2.ActionSupport;
//�ɹ�
public class Demo1Action  extends ActionSupport{

	public String execute() throws Exception {
		System.out.println("open ActionSupport");
		return SUCCESS;
	}
	
}
