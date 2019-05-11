package zkSocialNetworkProject.shetuan.dao;

import zkSocialNetworkProject.shetuan.domain.Introduction;

public interface CommIntroductDao {

	Introduction getCommIntroduct(String commId);

	void addCommIntroduct(Introduction i);
	
	void updateCommIntroduct(Introduction i);

	Introduction isIntroExist(String commId);

}
