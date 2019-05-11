package zkSocialNetworkProject.shetuan.service.serviceImp;

import org.hibernate.Session;
import org.hibernate.Transaction;

import zkSocialNetworkProject.shetuan.dao.CommPageDao;
import zkSocialNetworkProject.shetuan.dao.daoImp.CommPageDaoImp;
import zkSocialNetworkProject.shetuan.domain.CommPage;
import zkSocialNetworkProject.shetuan.service.CommPageService;
import zkSocialNetworkProject.utils.HibernateUtils;

public class CommPageServiceImp implements CommPageService{
	
	CommPageDao commPageDao = new CommPageDaoImp();

	@Override
	public CommPage getCommPage(String communityId) {
		// TODO Auto-generated method stub
		Session session = HibernateUtils.getCurSession();
		Transaction ts = session.beginTransaction();
		CommPage commPage = commPageDao.getCommPage(communityId);
		ts.commit();
		return commPage;
	}

	@Override
	public void addCommPage(CommPage commPage) {
		// TODO Auto-generated method stub
		Session session = HibernateUtils.getCurSession();
		Transaction ts = session.beginTransaction();
		commPageDao.addCommPage(commPage);
		ts.commit();
	}

	@Override
	public void updateCommPage(CommPage commPage) {
		// TODO Auto-generated method stub
		Session session = HibernateUtils.getCurSession();
		Transaction ts = session.beginTransaction();
		commPageDao.addCommPage(commPage);
		ts.commit();
	}


}
