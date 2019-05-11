package zkSocialNetworkProject.shetuan.dao;

import java.util.List;

import zkSocialNetworkProject.shetuan.domain.CommMemberMsg;
import zkSocialNetworkProject.shetuan.domain.Commin;
import zkSocialNetworkProject.shetuan.domain.Student;
import zkSocialNetworkProject.shetuan.domain.shetuanUser;

public interface CommMemberDao {

	List<CommMemberMsg> getCommMember(String commId);

	Object[] getHeadName(String uid);

	List<Student> findStudentList(String email) throws Exception;

	void addInfo(Commin commin)throws Exception;

	Object idntity(String identityId)throws Exception;
	
	public List<Commin> QuerymemberInfo() throws Exception;
	
	public List<Commin> QuerymemberInfoBydep(String deparment) throws Exception;
	
	public void deletememberInfo(String sid) throws Exception;

}
