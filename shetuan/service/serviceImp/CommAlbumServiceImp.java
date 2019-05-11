package zkSocialNetworkProject.shetuan.service.serviceImp;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import zkSocialNetworkProject.shetuan.dao.CommAlbumDao;
import zkSocialNetworkProject.shetuan.dao.daoImp.CommAlbumDaoImp;
import zkSocialNetworkProject.shetuan.domain.CommAlbum;
import zkSocialNetworkProject.shetuan.service.CommAlbumService;
import zkSocialNetworkProject.utils.HibernateUtils;

public class CommAlbumServiceImp implements CommAlbumService {
	
	CommAlbumDao commAlbumDao = new CommAlbumDaoImp();

	@Override
	public void addCommAlbum(CommAlbum commAlbum) {
		Session curSession = HibernateUtils.getCurSession();
		Transaction ts = curSession.beginTransaction();
		commAlbumDao.addAlbum(commAlbum);
		ts.commit();
	}

	@Override
	public List<CommAlbum> getCommAlbum(String uid) {
		// TODO Auto-generated method stub
		Session curSession = HibernateUtils.getCurSession();
		Transaction ts = curSession.beginTransaction();
		List<CommAlbum> commAlbumList = commAlbumDao.getCommAlbum(uid);
		ts.commit();
		return commAlbumList;
	}

	@Override
	public void deleteCommAlbum(String cid) {
		Session curSession = HibernateUtils.getCurSession();
		Transaction ts = curSession.beginTransaction();
		commAlbumDao.deleteAlbumPic(cid);
		commAlbumDao.deleteAlbum(cid);
		ts.commit();
		
	}

	@Override
	public void deleteOnePhoto(String cphotoPath) {
		Session curSession = HibernateUtils.getCurSession();
		Transaction ts = curSession.beginTransaction();
		commAlbumDao.deleteOnePhoto(cphotoPath);
		ts.commit();
	}

}
