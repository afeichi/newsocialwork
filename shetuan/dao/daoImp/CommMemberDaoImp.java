package zkSocialNetworkProject.shetuan.dao.daoImp;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.hibernate.Query;
import org.hibernate.Session;

import zkSocialNetworkProject.shetuan.dao.CommMemberDao;
import zkSocialNetworkProject.shetuan.domain.CommMemberMsg;
import zkSocialNetworkProject.shetuan.domain.Commin;
import zkSocialNetworkProject.shetuan.domain.Student;
import zkSocialNetworkProject.shetuan.domain.shetuanUser;
import zkSocialNetworkProject.utils.C3P0Util;
import zkSocialNetworkProject.utils.HibernateUtils;

public class CommMemberDaoImp implements CommMemberDao {

	@Override
	public List<CommMemberMsg> getCommMember(String commId) {
		Session curSession = HibernateUtils.getCurSession();
		String hql = "from CommMemberMsg where commId = ?";
		Query query = curSession.createQuery(hql);
		query.setParameter(0, commId);
		List<CommMemberMsg> list = query.list();
		return list;
	}

	@Override
	public Object[] getHeadName(String uid) {
		Session curSession = HibernateUtils.getCurSession();
		//待整合时修改
		String hql = "select head,name from user where uid =?";
		Query query = curSession.createQuery(hql);
		query.setParameter(0, uid);
		Object[] result = (Object[]) query.uniqueResult();
		return result;
	}

	public Object idntity(String identityId) throws Exception {
		QueryRunner runner =new QueryRunner(C3P0Util.getDataSource());
		String sql="update shetuan set status=? where memberstatus=?";
		runner.update(sql,identityId);
		return null;
	}


	public List<Student> findStudentList(String email) throws Exception {
		QueryRunner runner =new QueryRunner(C3P0Util.getDataSource());
		List<String> list =new ArrayList<String>();
		String sql="select * from student ";
		List<Student> studentList =runner.query(sql, new BeanListHandler<Student>(Student.class), list.toArray());
		return studentList;
	}


	public void addInfo(Commin commin) throws Exception {
		//获得连接池中的连接对象
		QueryRunner runner =new QueryRunner(C3P0Util.getDataSource());
		String sql="insert into Commin values(?,?,?,?)";
		Object[] params= {commin.getCommId(),commin.getSid(),commin.getFunctions(),commin.getDeparment()};
		runner.update(sql,params);
	}
	
	/*
	 * 查询所有成员信息
	 */
		public List<Commin> QuerymemberInfo() throws Exception {
			QueryRunner runner=new QueryRunner(C3P0Util.getDataSource());
			String sql ="select * from Commin";
			 return runner.query(sql, new BeanListHandler<Commin>(Commin.class));
		}

	/*
	 * 通锅成员的部门来查询部门的人
	 */
		public List<Commin> QuerymemberInfoBydep(String deparment) throws Exception {
			QueryRunner runner=new QueryRunner(C3P0Util.getDataSource());
			String sql="select * from Commin where deparment=?";
			return runner.query(sql, new BeanListHandler<Commin>(Commin.class), deparment);
		}


		public void deletememberInfo(String sid) throws Exception {
			QueryRunner runner=new QueryRunner(C3P0Util.getDataSource());
			String sql="delete from Commin where sid=?";
			runner.update(sql, sid);
		}



}
