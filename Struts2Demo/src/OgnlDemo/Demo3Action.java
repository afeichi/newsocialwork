package OgnlDemo;

import com.opensymphony.xwork2.ActionSupport;

public class Demo3Action extends ActionSupport {
	private String name;

	@Override
	public String execute() throws Exception {
		name="jerry";  //�����ݿ��в�ѯ
		return SUCCESS;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name=name;
	}
}
