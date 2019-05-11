package zkSocialNetworkProject.shetuan.service.serviceImp;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import zkSocialNetworkProject.domain.CommAdBox;
import zkSocialNetworkProject.domain.CommAdPic;
import zkSocialNetworkProject.domain.CommunityAD;
import zkSocialNetworkProject.domain.Tweet;
import zkSocialNetworkProject.domain.User;
import zkSocialNetworkProject.shetuan.dao.CommunityDao;
import zkSocialNetworkProject.shetuan.dao.daoImp.CommunityDaoImp;
import zkSocialNetworkProject.shetuan.service.CommunityService;
import zkSocialNetworkProject.utils.HibernateUtils;

public class CommunityServiceImp implements CommunityService{
	
	CommunityDao communityDao = new CommunityDaoImp();

	@Override
	public void publishCommunityAD(CommunityAD commAD) {
		try {
			Session session = HibernateUtils.getCurSession();
			Transaction ts = session.beginTransaction();
			communityDao.publishCommunityAD(commAD);
			ts.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			RuntimeException runException = new RuntimeException();
			throw runException;
		}
	}

	@Override
	public void deleteComAD(String commId) {
		try {
			Session session = HibernateUtils.getCurSession();
			Transaction ts = session.beginTransaction();
			communityDao.deleteComAd(commId);
			ts.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			RuntimeException exception = new RuntimeException();
			throw exception;
		}
	}

	@Override
	public List<CommAdBox> getNewCommAD() {
		try {
			List<CommAdBox> boxList = new ArrayList<CommAdBox>();
			Session session = HibernateUtils.getCurSession();
			Transaction ts = session.beginTransaction();
			List<CommunityAD> list = communityDao.getNewCommAD();
			for(CommunityAD commAD:list) {
				User user = communityDao.getUserByCommAD(commAD.getUid());
				CommAdBox commab = new CommAdBox();
				commab.setCommuAD(commAD);
				commab.setUser(user);
				boxList.add(commab);
			}
			ts.commit();
			return boxList;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			RuntimeException exception = new RuntimeException();
			throw exception;
		}
	}

	@Override
	public List<CommAdBox> getCommAD() {
		try {
			List<CommAdBox> boxList = new ArrayList<CommAdBox>();
			Session session = HibernateUtils.getCurSession();
			Transaction ts = session.beginTransaction();
			List<CommunityAD> list = communityDao.getCommAD();
			for(CommunityAD commAD:list) {
				User user = communityDao.getUserByCommAD(commAD.getUid());
				CommAdBox commab = new CommAdBox();
				commab.setCommuAD(commAD);
				commab.setUser(user);
				boxList.add(commab);
			}
			ts.commit();
			return boxList;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			RuntimeException exception = new RuntimeException();
			throw exception;
		}
	}
	
	@Override
	public CommunityAD showCommAdCon(String commId, String uid) {
		try {
			Session session = HibernateUtils.getCurSession();
			Transaction ts = session.beginTransaction();
			CommunityAD commAD = communityDao.getCommADByCommId(commId);
			List<String> pic = communityDao.getCommPic(commId);
			commAD.setCommPic(pic);
			ts.commit();
			return commAD;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			RuntimeException exception = new RuntimeException();
			throw exception;
		}
	}

	@Override
	public void publishCommADPic(CommAdPic commAdPic) {
		try {
			Session session = HibernateUtils.getCurSession();
			Transaction ts = session.beginTransaction();
			communityDao.saveCommPic(commAdPic);
			ts.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
