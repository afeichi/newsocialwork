package zkSocialNetworkProject.shetuan.domain;


//简介协会一览的图片
public class IntroScanPic {
	private String commId;
	private String pic_path;
	public IntroScanPic() {
		super();
		// TODO Auto-generated constructor stub
	}
	public IntroScanPic(String commId, String pic_path) {
		super();
		this.commId = commId;
		this.pic_path = pic_path;
	}
	public String getCommId() {
		return commId;
	}
	public void setCommId(String commId) {
		this.commId = commId;
	}
	public String getPic_path() {
		return pic_path;
	}
	public void setPic_path(String pic_path) {
		this.pic_path = pic_path;
	}
	
	
}
