package ApiDemo;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;
//�ɹ�����
public class Demo6Action extends ActionSupport implements ServletRequestAware{
	private HttpServletRequest request;

	@Override
	public String execute() throws Exception {
		System.out.println("ԭ��request��"+request);

		return SUCCESS;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;
	}
	

}
