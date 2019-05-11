package zkSocialNetworkProject.shetuan.service;

import zkSocialNetworkProject.shetuan.domain.CommPage;

public interface CommPageService {
	
	CommPage getCommPage(String communityId);

	void addCommPage(CommPage commPage);

	void updateCommPage(CommPage commPage);
}
