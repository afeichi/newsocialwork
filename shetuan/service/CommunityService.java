package zkSocialNetworkProject.shetuan.service;

import java.util.List;

import zkSocialNetworkProject.domain.CommAdBox;
import zkSocialNetworkProject.domain.CommAdPic;
import zkSocialNetworkProject.domain.CommunityAD;
import zkSocialNetworkProject.domain.Tweet;

public interface CommunityService {

	void publishCommunityAD(CommunityAD commAD);

	void deleteComAD(String commId);

	List<CommAdBox> getNewCommAD();

	List<CommAdBox> getCommAD();

	CommunityAD showCommAdCon(String commId, String uid);

	void publishCommADPic(CommAdPic commAdPic);

}
