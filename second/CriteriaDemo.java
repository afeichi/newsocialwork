package test.second;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import Hibernatetest.domin.Student;
import utils.HibernateUtils;

public class CriteriaDemo {
	@Test
	public void fun1() {
		Session session=HibernateUtils.openSession();
		Transaction tx=session.beginTransaction();
	Criteria criteria=session.createCriteria(Student.class);
	List<Student> list =criteria.list();
	System.out.println(list);
	tx.commit();
	session.close();
	}
}
