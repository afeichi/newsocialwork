package test.second;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.Test;

import Hibernatetest.domin.Student;
import utils.HibernateUtils;


public class StateDemo {
	@Test
	public void fun1() {
		//1获得Session
		Session session=HibernateUtils.openSession();
		//控制事务
		Transaction tx = session.beginTransaction();
		//操作
		Student c=new Student();
		c.setStudentname("wanieu");   //瞬时状态
		session.save(c);    //持久化状态，有id，有关联
		
		
		tx.commit();
		session.close();   //托管状态，有id，没有关联
	}
	public void fun2() {
		Session session=HibernateUtils.openSession();
		Transaction tx=session.beginTransaction();
		Student c=session.get(Student.class, 1l);
		c.setStudentname("zui");
		
	}
}
