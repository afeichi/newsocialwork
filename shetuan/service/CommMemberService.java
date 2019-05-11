package zkSocialNetworkProject.shetuan.service;

import java.util.List;


import zkSocialNetworkProject.shetuan.domain.CommMemberMsg;
import zkSocialNetworkProject.shetuan.domain.Commin;
import zkSocialNetworkProject.shetuan.domain.Student;

public interface CommMemberService {

	List<CommMemberMsg> getCommMember(String commId);

	List<Student> findStudentList(String email);

	boolean addInfo(Commin commin);

	Object idntity(String identityId);
	
	public List<Commin> QuerymemberInfo();
	
	public List<Commin> QuerymemberInfoBydep(String deparment);
	
	public boolean deletememberInfo(String sid);

}
