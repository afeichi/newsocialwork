package zkSocialNetworkProject.shetuan.dao.daoImp;

import org.hibernate.Session;

import zkSocialNetworkProject.shetuan.dao.CommIntroductDao;
import zkSocialNetworkProject.shetuan.domain.Introduction;
import zkSocialNetworkProject.utils.HibernateUtils;

public class CommIntroductDaoImp implements CommIntroductDao{
	
	
		//获取社团简介
		public Introduction getCommIntroduct(String commId) {
			Session session  = HibernateUtils.getCurSession();
			Introduction i = session.get(Introduction.class, commId);
			return i;		
		}
	
	
	
		//添加社团简介
		public void addCommIntroduct(Introduction i) {
			Session session = HibernateUtils.getCurSession();
			session.save(i);
		}
	
	
	
		//修改社团简介
		public void updateCommIntroduct(Introduction i) {
			Session session = HibernateUtils.getCurSession();
			session.update(i);
		}



		@Override
		public Introduction isIntroExist(String commId) {
			Session session = HibernateUtils.getCurSession();
			return session.get(Introduction.class,commId);
		}
}
