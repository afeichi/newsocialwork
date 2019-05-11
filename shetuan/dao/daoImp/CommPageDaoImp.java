package zkSocialNetworkProject.shetuan.dao.daoImp;



import org.hibernate.Session;

import zkSocialNetworkProject.shetuan.dao.CommPageDao;
import zkSocialNetworkProject.shetuan.domain.CommPage;
import zkSocialNetworkProject.utils.HibernateUtils;

public class CommPageDaoImp implements CommPageDao {
	
	@Override
	public CommPage getCommPage(String communityId) {
		Session session = HibernateUtils.getCurSession();
		CommPage commPage = session.get(CommPage.class, communityId);
		return commPage;
	}
	
	
	@Override
	public void addCommPage(CommPage commPage) {
		Session session = HibernateUtils.getCurSession();
		session.save(commPage);
	}
	
	
	@Override
	public void updateCommPage(CommPage commPage) {
		Session session = HibernateUtils.getCurSession();
		session.update(commPage);
	}
}
