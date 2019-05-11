
package zkSocialNetworkProject.shetuan.service.serviceImp;


import java.util.List;


import org.hibernate.Session;
import org.hibernate.Transaction;

import zkSocialNetworkProject.domain.CommAdPic;
import zkSocialNetworkProject.domain.CommunityAD;
import zkSocialNetworkProject.shetuan.dao.CommMyAdDao;
import zkSocialNetworkProject.shetuan.dao.daoImp.CommMyAdDaoImp;
import zkSocialNetworkProject.shetuan.service.CommMyAdService;
import zkSocialNetworkProject.utils.HibernateUtils;

public class CommMyAdServiceImp implements CommMyAdService {
	CommMyAdDao myAdDao = new CommMyAdDaoImp();
	
	@Override
	public void publishCommunityAD(CommunityAD commAD) {
			Session session = HibernateUtils.getCurSession();
			Transaction ts = session.beginTransaction();
			myAdDao.publishCommunityAD(commAD);
			for(String picPath:commAD.getCommPic()) {
				CommAdPic commPic = new CommAdPic(commAD.getCommId(),picPath);
				myAdDao.saveCommPic(commPic);
			}
			ts.commit();
	}
	
	@Override
	public void deleteCommAD(String commId) {
			Session session = HibernateUtils.getCurSession();
			Transaction ts = session.beginTransaction();
			myAdDao.deleteComAd(commId);
			ts.commit();
	}
	
	@Override
	public List<CommunityAD> getCommADByUid(String uid){
			Session session = HibernateUtils.getCurSession();
			Transaction ts = session.beginTransaction();
			List<CommunityAD> list = myAdDao.getCommADByUid(uid);
			for(CommunityAD commAD:list) {
				List<String> commPic = myAdDao.getCommPic(commAD.getCommId());
				commAD.setCommPic(commPic);
			}
			ts.commit();
			return list;
	}
}
