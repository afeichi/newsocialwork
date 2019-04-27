package test.first;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import Hibernatetest.domin.Student;

public class Demo4 {
	//学习Session对象
	//session对象功能: 表达hibernate框架与数据库之间的连接(会话).session类似于
//					JDBC年代的connection对象. 还可以完成对数据库中数据的增删改查操作.
//					session是hibernate操作数据库的核心对象
//	public void fun1() {
//		Configuration conf =new Configuration();
//		SessionFactory sf =conf.buildSessionFactory();
//		//获得session！
//		Session session=sf.openSession();
//		//开启事务并获得操作事务的对象
//		Transaction tx2=session.beginTransaction();
//
//		tx2.commit();//提交事务
//		tx2.rollback();//回滚事务
//		session.close();//释放资源
//		sf.close();//释放资源
//	}
	
	@Test
	//添加session
	public void fun2() {
		Configuration conf =new Configuration().configure();
		SessionFactory sf=conf.buildSessionFactory();
		//获得session
		Session session=sf.openSession();	
		Transaction tx2=session.beginTransaction();
		Student c=new Student();
		c.setStudentname("nieyi");


		
		session.save(c);
		tx2.commit(); //提交事务
		session.close();  // 释放资源
		sf.close();
	}
}
