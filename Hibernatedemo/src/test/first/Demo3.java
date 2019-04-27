package test.first;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Demo3 {
	public void fun1() {
		//调用空参构造
		Configuration conf =new Configuration();
		//2.读取指定主配置文件  => 空参加载方法,加载src下的hibernate.cfg.xml文件
		conf.configure();
		//3读取指定orm元数据，如果主配置中已经引入映射配置，不需要手动加入
//		conf.addResource(resourceName);
//		conf.addClass(persistentClass);
		
		//4.根据配置信息，创建SessionFactory对象
		SessionFactory sf= conf.buildSessionFactory();
		sf.openSession();
		sf.getCurrentSession();
	}
}
