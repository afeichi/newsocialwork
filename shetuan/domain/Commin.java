package zkSocialNetworkProject.shetuan.domain;

public class Commin {
	private String commId;  //社团id
	private String sid;	//用户id
	private String functions; //成员职务
	private String deparment; //成员所属的部门
	public String getCommId() {
		return commId;
	}
	public void setCommId(String commId) {
		this.commId = commId;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getFunctions() {
		return functions;
	}
	public void setFunctions(String functions) {
		this.functions = functions;
	}
	public String getDeparment() {
		return deparment;
	}
	public void setDeparment(String deparment) {
		this.deparment = deparment;
	}
}
