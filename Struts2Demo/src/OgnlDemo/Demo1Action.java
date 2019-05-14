package OgnlDemo;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import ognl.Ognl;
import ognl.OgnlContext;
import ognl.OgnlException;

public class Demo1Action {
	@Test
	public void fun1() throws Exception {
		//准备OGNLContext
		//准备root
		User rootUser =new User("tom",18);
		//准备Context
		Map<String,User> context=new HashMap<String,User>();
		context.put("user1", new User("jack",19));
		context.put("user2", new User("rose",22));
		OgnlContext oc=new OgnlContext();
		//将rootuser作为root部分
		oc.setRoot(rootUser);
		//将context这个map作为Context部分
		oc.setValues(context);
		//书写OGNL
		Ognl.getValue("",oc,oc.getRoot());
	}
	
	
	//基本语法演示
	//取出root中的属性值
	@Test
	public void fun2() throws OgnlException {
		User rootUser =new User("tom",18);
		Map<String,User> context=new HashMap<String,User>();
		context.put("user1", new User("jacl",19));
		context.put("user2", new User("jacc",20));
		OgnlContext oc=new OgnlContext();
		oc.setRoot(rootUser);
		oc.setValues(context);
		//书写ognl
		//去除root中user对象的name属性
		String name=(String) Ognl.getValue("name",oc,oc.getRoot());
		Integer age=(Integer) Ognl.getValue("age",oc,oc.getRoot());
		System.out.println(name);
	}
	
	public void fun3() throws OgnlException {
		User rootUser=new User("tom",18);
		Map<String,User> context=new HashMap<String,User>();
		context.put("user1", new User("jack",18));
		context.put("user2", new User("mary",22));
		OgnlContext oc =new OgnlContext();
		oc.setRoot(rootUser);
		oc.setValues(context);
		//取出Context中键位User1对象的name属性
		String name=(String) Ognl.getValue("#user1.name",oc,oc.getRoot());
		String name2=(String) Ognl.getValue("#user2.name",oc,oc.getRoot());
		Integer age=(Integer) Ognl.getValue("#user2.age", oc,oc.getRoot());
		System.out.println(name);
		System.out.println(name2);
		System.out.println(age);
	}
	public void fun4() throws Exception {
		User rootUser=new User("tom",18);
		Map<String,User> context=new HashMap<String,User>();
		context.put("user1", new User("jack",18));
		context.put("user2", new User("rose",22));
		OgnlContext oc=new OgnlContext();
		oc.getRoot();
		oc.getValues();
		//书写rgol
		//将root中的User对象的name属性赋值
		Ognl.getValue("name='jerry'",oc,oc.getRoot());
		String name=(String) Ognl.getValue("name", oc,oc.getRoot());
		String name2=(String) Ognl.getValue("#user1.name='哥哥',#user1.name", oc,oc.getRoot());
		
		System.out.println(name);
		System.out.println(name2);
	}
	
	public void fun5() throws Exception {
		//准备root
		User rootUser=new User("tom",18);
		Map<String,User> context=new HashMap<String,User>();
		context.put("user1", new User("jack",18));
		context.put("user2", new User("rose",22));
		OgnlContext oc=new OgnlContext();
		oc.setRoot(rootUser);
		oc.setValues(context);
		
		//书写ognl
		//调用root中user对象的setName方法
		Ognl.getValue("setName('leilei')",oc,oc.getRoot());
		String name=(String) Ognl.getValue("name", oc,oc.getRoot());
		String name2=(String) Ognl.getValue("#user1.setName('lucy'),#user1.getName()" ,oc,oc.getRoot());
		System.out.println(name);
		System.out.println(name2);
	}
	public void fun7() throws Exception {
		User rootUser =new User("tom",18);
		Map<String,User>context=new HashMap<String,User>();
		context.put("user1", new User("jack",18));
		context.put("user2", new User("rose",22));
		OgnlContext oc =new OgnlContext();
		oc.setRoot(rootUser);
		oc.setValues(context);
		//书写OGNL
		//创建list对象
		Integer size=(Integer) Ognl.getValue("{'tom','jerry','jack','rose'}.size()", oc,oc.getRoot());
		String name = (String) Ognl.getValue("{'tom','jerry','jack','rose'}[0]", oc, oc.getRoot());
		String name2 = (String) Ognl.getValue("{'tom','jerry','jack','rose'}.get(1)", oc, oc.getRoot());
		//创建Map对象
		Integer size2 = (Integer) Ognl.getValue("#{'name':'tom','age':18}.size()", oc, oc.getRoot());
		String name3  = (String) Ognl.getValue("#{'name':'tom','age':18}['name']", oc, oc.getRoot());
		Integer age  = (Integer) Ognl.getValue("#{'name':'tom','age':18}.get('age')", oc, oc.getRoot());
		System.out.println(size2);
		System.out.println(name3);
		System.out.println(age);
	}
}
