/**
 * 
 */
package zkSocialNetworkProject.shetuan.domain;

/**
 * @author Jason
 *
 */
//暂时弃用
public class CommAlbumPic {
	private String pid;// 代理主键
	private String cid;// 所属的相片集的id
	private String cphotoPath;// 相片路径

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getCphotoPath() {
		return cphotoPath;
	}

	public void setCphotoPath(String cphotoPath) {
		this.cphotoPath = cphotoPath;
	}

	public CommAlbumPic(String pid, String cid, String cphotoPath) {
		super();
		this.pid = pid;
		this.cid = cid;
		this.cphotoPath = cphotoPath;
	}

	public CommAlbumPic() {
		super();
		// TODO Auto-generated constructor stub
	}

}
