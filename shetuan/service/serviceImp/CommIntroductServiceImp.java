package zkSocialNetworkProject.shetuan.service.serviceImp;



import org.hibernate.Session;
import org.hibernate.Transaction;

import zkSocialNetworkProject.shetuan.dao.CommIntroductDao;
import zkSocialNetworkProject.shetuan.dao.daoImp.CommIntroductDaoImp;
import zkSocialNetworkProject.shetuan.domain.Introduction;
import zkSocialNetworkProject.shetuan.service.CommIntroductService;
import zkSocialNetworkProject.utils.HibernateUtils;

public class CommIntroductServiceImp implements CommIntroductService{
	
	CommIntroductDao commIntroductDao = new CommIntroductDaoImp();

	@Override
	public Introduction getCommIntroduct(String commId) {
		Session session = HibernateUtils.getCurSession();
		Transaction ts = session.beginTransaction();
		try {
			Introduction introduction = commIntroductDao.getCommIntroduct(commId);
			ts.commit();
			return introduction;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ts.rollback();
			RuntimeException re = new RuntimeException();
			throw re;
		}
	}

	@Override
	public void addCommIntroduct(Introduction i) {
		Session session = HibernateUtils.getCurSession();
		Transaction ts = session.beginTransaction();
		try {
			commIntroductDao.addCommIntroduct(i);
			ts.commit();
		} catch (Exception e) {
			e.printStackTrace();
			ts.rollback();
		}
		
	}

	@Override
	public void updateCommIntroduct(Introduction i) {
		Session session = HibernateUtils.getCurSession();
		Transaction ts = session.beginTransaction();
		try {
			commIntroductDao.updateCommIntroduct(i);
			ts.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ts.rollback();
		}
	}

	@Override
	public Introduction isIntroExist(String commId) {
		Session session = HibernateUtils.getCurSession();
		Transaction ts = session.beginTransaction();
		Introduction introduction = commIntroductDao.isIntroExist(commId);
		ts.commit();
		return introduction;
	}

}
