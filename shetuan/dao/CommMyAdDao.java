package zkSocialNetworkProject.shetuan.dao;

import java.util.List;

import zkSocialNetworkProject.domain.CommAdPic;
import zkSocialNetworkProject.domain.CommunityAD;

public interface CommMyAdDao {
	
	void publishCommunityAD(CommunityAD commuAD1);

	void deleteComAd(String commId);
	
	void saveCommPic(CommAdPic CommAdPic);
	
	List<CommunityAD> getCommAD();
	
	List<String> getCommPic(String commId);
	
	List<CommunityAD> getCommADByUid(String uid);

}
