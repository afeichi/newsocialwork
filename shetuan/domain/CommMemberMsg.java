package zkSocialNetworkProject.shetuan.domain;

import java.io.Serializable;

public class CommMemberMsg implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String uid;// 成员Id
	private String commId;// 社团Id
	private String job;// 职务
	private String department;// 部门

	private String head;// 头像路径
	private String name;// 成员昵称

	
	
	public String getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getCommId() {
		return commId;
	}

	public void setCommId(String commId) {
		this.commId = commId;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public CommMemberMsg() {
		super();
		// TODO Auto-generated constructor stub
	}

}
