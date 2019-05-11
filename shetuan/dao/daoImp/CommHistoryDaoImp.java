package zkSocialNetworkProject.shetuan.dao.daoImp;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import zkSocialNetworkProject.shetuan.dao.CommHistoryDao;
import zkSocialNetworkProject.shetuan.domain.CommHistory;
import zkSocialNetworkProject.shetuan.domain.CommHistoryPic;
import zkSocialNetworkProject.utils.HibernateUtils;

public class CommHistoryDaoImp implements CommHistoryDao{

	@Override
	public void addHistory(CommHistory commHistory) {
		// TODO Auto-generated method stub
		Session session = HibernateUtils.getCurSession();
		session.save(commHistory);
	}

	@Override
	public List<CommHistory> getCommHistory(String communityId) {
		// TODO Auto-generated method stub
		Session session = HibernateUtils.getCurSession();
		String hql = "from CommHistory where commId = ?";
		Query query = session.createQuery(hql);
		List<CommHistory> list = query.setParameter(0, communityId).list();
		return list;
	}
	
	@Override
	public List<String> getCommHistoryPic(String historyId) {
		// TODO Auto-generated method stub
		Session session = HibernateUtils.getCurSession();
		String hql = "select picPath from CommHistoryPic where historyId = ?";
		Query query = session.createQuery(hql);
		List<String> list = query.setParameter(0, historyId).list();
		return list;
	}

	@Override
	public void deleteHistory(String historyId) {
		// TODO Auto-generated method stub
		Session session = HibernateUtils.getCurSession();
		String hql = "delete from CommHistory where historyId = ?";
		Query query = session.createQuery(hql);
		query.setParameter(0, historyId);
		query.executeUpdate();
	}

	@Override
	public void deleteHistoryPic(String historyId) {
		Session session = HibernateUtils.getCurSession();
		String hql = "delete from CommHistoryPic where historyId = ?";
		Query query = session.createQuery(hql);
		query.setParameter(0, historyId);
		query.executeUpdate();
	}

	@Override
	public CommHistory getCommHistoryDetail(String historyId) {
		// TODO Auto-generated method stub
		Session session = HibernateUtils.getCurSession();
		String hql = "select picPath form CommHistory where historyId = ?";
		Query query = session.createQuery(hql);
		CommHistory commHistory = (CommHistory) query.setParameter(0, historyId).uniqueResult();
		return commHistory;
	}

	@Override
	public void addHistoryPic(CommHistoryPic commHistoryPic) {
		// TODO Auto-generated method stub
		Session session = HibernateUtils.getCurSession();
		session.save(commHistoryPic);
	}
	
}
