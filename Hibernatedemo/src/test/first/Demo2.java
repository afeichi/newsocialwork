package test.first;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class Demo2 {
	@Test
	public void fun1() {
		//1.创建，调用空参构造
		Configuration conf =new Configuration();
		//读取配置文件，加载src下的hibernate.cfg.xml
		 conf.configure();
		 //根据配置信息，创建SessionFactory对象
		 SessionFactory fg=conf.buildSessionFactory();
	}
}
