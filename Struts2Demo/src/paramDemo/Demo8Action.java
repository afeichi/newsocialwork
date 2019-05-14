package paramDemo;

import java.util.Date;

import com.opensymphony.xwork2.ActionSupport;

public class Demo8Action extends ActionSupport {
//struts获得参数
	//每次请求Action都会创建新的Action实例对象
	public Demo8Action() {
		super();
		System.out.println("我创建了8");
	}
	//准备与参数键名称相同的属性
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
		System.out.println("name参数值："+name+",age参数值"+age+",生日："+birthday);
		return SUCCESS;
	}
	
	
}
