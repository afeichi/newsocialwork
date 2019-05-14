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
		//׼��OGNLContext
		//׼��root
		User rootUser =new User("tom",18);
		//׼��Context
		Map<String,User> context=new HashMap<String,User>();
		context.put("user1", new User("jack",19));
		context.put("user2", new User("rose",22));
		OgnlContext oc=new OgnlContext();
		//��rootuser��Ϊroot����
		oc.setRoot(rootUser);
		//��context���map��ΪContext����
		oc.setValues(context);
		//��дOGNL
		Ognl.getValue("",oc,oc.getRoot());
	}
	
	
	//�����﷨��ʾ
	//ȡ��root�е�����ֵ
	@Test
	public void fun2() throws OgnlException {
		User rootUser =new User("tom",18);
		Map<String,User> context=new HashMap<String,User>();
		context.put("user1", new User("jacl",19));
		context.put("user2", new User("jacc",20));
		OgnlContext oc=new OgnlContext();
		oc.setRoot(rootUser);
		oc.setValues(context);
		//��дognl
		//ȥ��root��user�����name����
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
		//ȡ��Context�м�λUser1�����name����
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
		//��дrgol
		//��root�е�User�����name���Ը�ֵ
		Ognl.getValue("name='jerry'",oc,oc.getRoot());
		String name=(String) Ognl.getValue("name", oc,oc.getRoot());
		String name2=(String) Ognl.getValue("#user1.name='���',#user1.name", oc,oc.getRoot());
		
		System.out.println(name);
		System.out.println(name2);
	}
	
	public void fun5() throws Exception {
		//׼��root
		User rootUser=new User("tom",18);
		Map<String,User> context=new HashMap<String,User>();
		context.put("user1", new User("jack",18));
		context.put("user2", new User("rose",22));
		OgnlContext oc=new OgnlContext();
		oc.setRoot(rootUser);
		oc.setValues(context);
		
		//��дognl
		//����root��user�����setName����
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
		//��дOGNL
		//����list����
		Integer size=(Integer) Ognl.getValue("{'tom','jerry','jack','rose'}.size()", oc,oc.getRoot());
		String name = (String) Ognl.getValue("{'tom','jerry','jack','rose'}[0]", oc, oc.getRoot());
		String name2 = (String) Ognl.getValue("{'tom','jerry','jack','rose'}.get(1)", oc, oc.getRoot());
		//����Map����
		Integer size2 = (Integer) Ognl.getValue("#{'name':'tom','age':18}.size()", oc, oc.getRoot());
		String name3  = (String) Ognl.getValue("#{'name':'tom','age':18}['name']", oc, oc.getRoot());
		Integer age  = (Integer) Ognl.getValue("#{'name':'tom','age':18}.get('age')", oc, oc.getRoot());
		System.out.println(size2);
		System.out.println(name3);
		System.out.println(age);
	}
}
