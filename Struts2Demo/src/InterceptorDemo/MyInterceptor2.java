package InterceptorDemo;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
//������ʽ2: �̳�AbstractInterceptor -> struts2������
//�����ǿ�ʵ����init �� destory����. �����������Ҫʵ������������,�Ϳ���ֻʵ��intercept����
public class MyInterceptor2 extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		
		return null;
	}

}
