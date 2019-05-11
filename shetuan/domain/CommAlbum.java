package zkSocialNetworkProject.shetuan.domain;



/**
 * @author Jason
 *
 */
public class CommAlbum {
	private String pid;//该相片代理主键
	private String uid;//该相片的社团拥有者
	private String cphoto;//图片路径
	private String date;//上传时间
	

	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getCphoto() {
		return cphoto;
	}
	public void setCphoto(String cphoto) {
		this.cphoto = cphoto;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
}
