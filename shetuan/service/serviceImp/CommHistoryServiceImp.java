package zkSocialNetworkProject.shetuan.service.serviceImp;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import zkSocialNetworkProject.shetuan.dao.CommHistoryDao;
import zkSocialNetworkProject.shetuan.dao.daoImp.CommHistoryDaoImp;
import zkSocialNetworkProject.shetuan.domain.CommHistory;
import zkSocialNetworkProject.shetuan.domain.CommHistoryPic;
import zkSocialNetworkProject.shetuan.service.CommHistoryService;
import zkSocialNetworkProject.utils.HibernateUtils;

public class CommHistoryServiceImp implements CommHistoryService {
	
	CommHistoryDao commHistoryDao = new CommHistoryDaoImp();

	@Override
	public void addHistory(CommHistory commHistory) {
		Session session = HibernateUtils.getCurSession();
		Transaction ts = session.beginTransaction();
		commHistoryDao.addHistory(commHistory);
		for(String picPath:commHistory.getPicList()) {
			CommHistoryPic chp = new CommHistoryPic(commHistory.getHistoryId(),picPath);
			commHistoryDao.addHistoryPic(chp);
		}
		ts.commit();
	}

	@Override
	public List<CommHistory> getCommHistory(String communityId) {
		// TODO Auto-generated method stub
		Session session = HibernateUtils.getCurSession();
		Transaction ts = session.beginTransaction();
		List<CommHistory> list = commHistoryDao.getCommHistory(communityId);
		ts.commit();
		return list;
	}

	@Override
	public void deleteHistory(String historyId) {
		// TODO Auto-generated method stub
		Session session = HibernateUtils.getCurSession();
		Transaction ts = session.beginTransaction();
		commHistoryDao.deleteHistoryPic(historyId);
		commHistoryDao.deleteHistory(historyId);
		ts.commit();
		
	}

	@Override
	public CommHistory getCommHistoryDetail(String historyId) {
		// TODO Auto-generated method stub
		Session session = HibernateUtils.getCurSession();
		Transaction ts = session.beginTransaction();
		CommHistory chd = commHistoryDao.getCommHistoryDetail(historyId);
		List<String> chp = commHistoryDao.getCommHistoryPic(chd.getHistoryId());
		chd.setPicList(chp);
		ts.commit();
		return chd;
	}

}
