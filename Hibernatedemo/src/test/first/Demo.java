package test.first;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import Hibernatetest.domin.Student;



//测试HIbernate框架
public class Demo {
	@Test
	public void fun1() {
		Configuration conf =new Configuration().configure();
		SessionFactory sessionFactory = conf.buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		Student c=new Student();
		c.setStudentid("2");
		c.setStudentname("jjyy");
		c.setStudentnumber("11");
		c.setStudentphone("3");
		c.setStudentsex("123");
		
		session.save(c); //执行保存
		tx.commit();
		session.close();
		sessionFactory.close();
	}
}
