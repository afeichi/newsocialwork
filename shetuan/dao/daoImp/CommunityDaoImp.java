package zkSocialNetworkProject.shetuan.dao.daoImp;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;

import zkSocialNetworkProject.domain.CommAdPic;
import zkSocialNetworkProject.domain.CommunityAD;
import zkSocialNetworkProject.domain.Tweet;
import zkSocialNetworkProject.domain.TweetPic;
import zkSocialNetworkProject.domain.User;
import zkSocialNetworkProject.shetuan.dao.CommunityDao;
import zkSocialNetworkProject.utils.HibernateUtils;

public class CommunityDaoImp implements CommunityDao {

	@Override
	public void publishCommunityAD(CommunityAD commAD) {
		Session session = HibernateUtils.getCurSession();
		session.save(commAD);
	}

	@Override
	public void deleteComAd(String commId) {
		Session session = HibernateUtils.getCurSession();
		CommunityAD communityAD = session.get(CommunityAD.class,commId);
		session.delete(communityAD);
	}

	@Override
	public List<CommunityAD> getNewCommAD() {
		Session session = HibernateUtils.getCurSession();
		Criteria criteria = session.createCriteria(CommunityAD.class);
		criteria.setFirstResult(0);
		criteria.setMaxResults(3);
		List<CommunityAD> list = criteria.list();
		return list;
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
	public User getUserByCommAD(String commId) {
		Session session = HibernateUtils.getCurSession();
		User user = session.get(User.class, commId);
		return user;
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
	public void saveCommPic(CommAdPic CommAdPic) {
		Session session = HibernateUtils.getCurSession();
		session.save(CommAdPic);
	}

	@Override
	public CommunityAD getCommADByCommId(String commId) {
		Session session = HibernateUtils.getCurSession();
		String hql = "from CommunityAD where commId = ?";
		Query query = session.createQuery(hql);
		query.setParameter(0, commId);
		CommunityAD commAD = (CommunityAD) query.uniqueResult();
		return commAD;
	}
	
}
