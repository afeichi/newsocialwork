package zkSocialNetworkProject.shetuan.domain;

public class CommHistoryPic {
	private String historyId;
	private String picPath;

	public String getHistoryId() {
		return historyId;
	}

	public void setHistoryId(String historyId) {
		this.historyId = historyId;
	}

	public String getPicPath() {
		return picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

	public CommHistoryPic() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CommHistoryPic(String historyId, String picPath) {
		super();
		this.historyId = historyId;
		this.picPath = picPath;
	}

	
}
