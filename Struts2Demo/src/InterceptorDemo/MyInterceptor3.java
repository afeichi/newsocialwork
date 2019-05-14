package InterceptorDemo;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

//�̳�:MethodFilterInterceptor ��������������
//����: �������������صķ���.
//	������Щ������Ҫ����.
//	������Щ��������Ҫ����
public class MyInterceptor3 extends MethodFilterInterceptor{

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		//ǰ����
		System.out.println("MyInterceptor3 !");
		//����		
		String result = invocation.invoke();
		//����
		System.out.println("MyInterceptor3 !");
		
		return result;
	}

}
