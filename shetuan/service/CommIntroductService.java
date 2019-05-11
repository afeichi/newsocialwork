package zkSocialNetworkProject.shetuan.service;

import zkSocialNetworkProject.shetuan.domain.Introduction;

public interface CommIntroductService {

	Introduction getCommIntroduct(String commId);

	void addCommIntroduct(Introduction i);
	
	void updateCommIntroduct(Introduction i);

	Introduction isIntroExist(String commId);
}
