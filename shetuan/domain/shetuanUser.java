package zkSocialNetworkProject.shetuan.domain;

public class shetuanUser {
	private String userid;			//userid
	private String administationid; //管理人id
	private String username;    //社团名字
	private String password;    //社团用户的密码
	private String evident;   //确定为社团管理人的凭证
	public String getEvident() {
		return evident;
	}
	public void setEvident(String evident) {
		this.evident = evident;
	}
	private String adname;   //管理人的名字
	private String adphone;    //管理人的联系方式
	private String Email;     //管理人的邮箱
	private int state;//是否激活
	private String code;//激活码


	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getAdministationid() {
		return administationid;
	}
	public void setAdministationid(String administationid) {
		this.administationid = administationid;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getAdname() {
		return adname;
	}
	public void setAdname(String adname) {
		this.adname = adname;
	}
	public String getAdphone() {
		return adphone;
	}
	public void setAdphone(String adphone) {
		this.adphone = adphone;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}



}
