package zkSocialNetworkProject.shetuan.dao.daoImp;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;

import zkSocialNetworkProject.domain.CommAdPic;
import zkSocialNetworkProject.domain.CommunityAD;
import zkSocialNetworkProject.shetuan.dao.CommMyAdDao;
import zkSocialNetworkProject.utils.HibernateUtils;

public class CommMyAdDaoImp implements CommMyAdDao {
	
	@Override
	public void publishCommunityAD(CommunityAD commAD) {
		Session session = HibernateUtils.getCurSession();
		session.save(commAD);
	}
	
	@Override
	public void saveCommPic(CommAdPic CommAdPic) {
		Session session = HibernateUtils.getCurSession();
		session.save(CommAdPic);
	}

	@Override
	public void deleteComAd(String commId) {
		Session session = HibernateUtils.getCurSession();
		CommunityAD communityAD = session.get(CommunityAD.class,commId);
		session.delete(communityAD);
	}
	
	@Override
	public List<CommunityAD> getCommAD() {
		Session session = HibernateUtils.getCurSession();
		Criteria criteria = session.createCriteria(CommunityAD.class);
		criteria.setFirstResult(0);
		List<CommunityAD> list = criteria.list();
		return list;
	}
	
	@Override
	public List<String> getCommPic(String commId) {
		Session session  = HibernateUtils.getCurSession();
		String hql = "from CommAdPic where commId = ?";
		Query query = session.createQuery(hql);
		query.setParameter(0, commId);
		List<String> list = query.list();
		return list;
	}


	@Override
	public List<CommunityAD> getCommADByUid(String uid) {
		Session session = HibernateUtils.getCurSession();
		String hql = "from CommunityAD where uid = ?";
		Query query = session.createQuery(hql);
		query.setParameter(0, uid);
		List<CommunityAD> list = query.list();
		return list;
	}

}
