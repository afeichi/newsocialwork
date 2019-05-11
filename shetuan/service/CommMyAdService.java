package zkSocialNetworkProject.shetuan.service;


import java.util.List;
import zkSocialNetworkProject.domain.CommunityAD;

public interface CommMyAdService {

	void publishCommunityAD(CommunityAD commAD);

	void deleteCommAD(String commId);

	List<CommunityAD> getCommADByUid(String uid);

}
