package zkSocialNetworkProject.shetuan.domain;

import java.util.List;

public class Introduction {
	private String commId;//协会id
	private String founding_time;//成立时间
	private String idea_culture;//理念文化
	private String special_activity;//品牌活动
	private String detail_intro;//详情介绍
	
	
	public String getCommId() {
		return commId;
	}
	public void setCommId(String commId) {
		this.commId = commId;
	}
	public String getFounding_time() {
		return founding_time;
	}
	public void setFounding_time(String founding_time) {
		this.founding_time = founding_time;
	}
	public String getIdea_culture() {
		return idea_culture;
	}
	public void setIdea_culture(String idea_culture) {
		this.idea_culture = idea_culture;
	}
	public String getSpecial_activity() {
		return special_activity;
	}
	public void setSpecial_activity(String special_activity) {
		this.special_activity = special_activity;
	}
	public String getDetail_intro() {
		return detail_intro;
	}
	public void setDetail_intro(String detail_intro) {
		this.detail_intro = detail_intro;
	}
	public Introduction(String commId, String founding_time, String idea_culture, String special_activity,
			String detail_intro, List<String> comm_scan) {
		super();
		this.commId = commId;
		this.founding_time = founding_time;
		this.idea_culture = idea_culture;
		this.special_activity = special_activity;
		this.detail_intro = detail_intro;
	}
	public Introduction() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
