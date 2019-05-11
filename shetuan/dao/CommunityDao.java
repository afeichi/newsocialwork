package zkSocialNetworkProject.shetuan.dao;

import java.util.List;

import zkSocialNetworkProject.domain.CommAdPic;
import zkSocialNetworkProject.domain.CommunityAD;
import zkSocialNetworkProject.domain.User;

public interface CommunityDao {

	void publishCommunityAD(CommunityAD commuAD1);

	void deleteComAd(String commId);

	List<CommunityAD> getNewCommAD();

	List<CommunityAD> getCommAD();

	User getUserByCommAD(String commId);

	List<String> getCommPic(String commId);

	void saveCommPic(CommAdPic CommAdPic);

	CommunityAD getCommADByCommId(String commId);

}
