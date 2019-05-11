package zkSocialNetworkProject.shetuan.service.serviceImp;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import zkSocialNetworkProject.shetuan.dao.CommMemberDao;
import zkSocialNetworkProject.shetuan.dao.daoImp.CommMemberDaoImp;
import zkSocialNetworkProject.shetuan.domain.CommMemberMsg;
import zkSocialNetworkProject.shetuan.domain.Commin;
import zkSocialNetworkProject.shetuan.domain.Student;
import zkSocialNetworkProject.shetuan.domain.shetuanUser;
import zkSocialNetworkProject.shetuan.service.CommMemberService;
import zkSocialNetworkProject.utils.HibernateUtils;

public class CommMemberServiceImp implements CommMemberService {

	CommMemberDao commMemberDao = new CommMemberDaoImp();

	@Override
	public List<CommMemberMsg> getCommMember(String commId) {
		// TODO Auto-generated method stub
		Session curSession = HibernateUtils.getCurSession();
		Transaction ts = curSession.beginTransaction();
		List<CommMemberMsg> list = commMemberDao.getCommMember(commId);
		for (CommMemberMsg member : list) {
			// 将成员头像和昵称查询出来放入对象中
			Object[] result = commMemberDao.getHeadName(member.getUid());
			member.setHead((String) result[0]);
			member.setName((String) result[1]);
		}
		ts.commit();
		return list;
	}


	public List<Student> findStudentList(String email) {
		List<Student> list = null;
		try {
			list = commMemberDao.findStudentList(email);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (list != null) {
			return list;
		} else {
			System.out.println("没有该学生信息");
		}
		return null;
	}

	public boolean addInfo(Commin commin) {
		try {
			commMemberDao.addInfo(commin);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Object idntity(String identityId) {
		try {
			return commMemberDao.idntity(identityId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public List<Commin> QuerymemberInfo() {
		try {
			commMemberDao.QuerymemberInfo();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Commin> QuerymemberInfoBydep(String deparment) {
		try {
			return commMemberDao.QuerymemberInfoBydep(deparment);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean deletememberInfo(String sid) {
	 try {
		commMemberDao.deletememberInfo(sid);
	} catch (Exception e) {
		e.printStackTrace();
	}
	 return true;
	}



}
