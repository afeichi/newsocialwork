package paramDemo;

import java.util.Date;

import com.opensymphony.xwork2.ActionSupport;

public class Demo8Action extends ActionSupport {
//struts��ò���
	//ÿ������Action���ᴴ���µ�Actionʵ������
	public Demo8Action() {
		super();
		System.out.println("�Ҵ�����8");
	}
	//׼���������������ͬ������
	private String name;
	private Integer age;
	private Date birthday;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String execute() throws Exception {
		System.out.println("name����ֵ��"+name+",age����ֵ"+age+",���գ�"+birthday);
		return SUCCESS;
	}
	
	
}
