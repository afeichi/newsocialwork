package test.second;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import Hibernatetest.domin.Student;
import utils.HibernateUtils;

public class HqlDemo {
	
	@Test
	public void fun1() {
		Session session=HibernateUtils.openSession();
		Transaction tx=session.beginTransaction();
		//操作
		String hql=" from Student"; //省略了secelt*
		//根据HQL语句创建查询对象
		Query query=session.createQuery(hql);
		//3根据查询对象获得查询结果
		List<Student> list=query.list();
		System.out.println(list);
		tx.commit();
		session.close();
	}
	//条件查询
	public void fun2() {
		Session session =HibernateUtils.openSession();
		Transaction tx=session.beginTransaction();
		String hql="from Student where studentid=?";
		//2根据HQL语句创建查询对象
		Query query=session.createQuery(hql);
		query.setParameter(0,1l);
		Student c=(Student) query.uniqueResult();
		System.out.println(c);
		tx.commit();
		session.close();
	}
}
