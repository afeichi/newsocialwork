package zkSocialNetworkProject.shetuan.dao;

import zkSocialNetworkProject.shetuan.domain.CommPage;

public interface CommPageDao {

	CommPage getCommPage(String communityId);

	void addCommPage(CommPage commPage);

	void updateCommPage(CommPage commPage);

}
