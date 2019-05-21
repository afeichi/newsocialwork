package bingo.dao;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import bingo.domain.User;

public class UserDao{
	
	private SessionFactory sessionFactory;
	
	public void setsessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}


	public User getUserByCodeName(String codeName) {
				Session session = sessionFactory.openSession();
				String hql = "from User where user_code = ?";
				Query query = session.createQuery(hql);
				query.setParameter(0, codeName);
				User user = (User) query.uniqueResult();
				return user;
	}
	
}
