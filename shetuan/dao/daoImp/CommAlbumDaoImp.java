
package zkSocialNetworkProject.shetuan.dao.daoImp;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.hibernate.Query;
import org.hibernate.Session;

import zkSocialNetworkProject.shetuan.dao.CommAlbumDao;
import zkSocialNetworkProject.shetuan.domain.CommAlbum;
import zkSocialNetworkProject.shetuan.domain.CommAlbumPic;
import zkSocialNetworkProject.shetuan.domain.Commin;
import zkSocialNetworkProject.shetuan.domain.Student;
import zkSocialNetworkProject.shetuan.domain.shetuanUser;
import zkSocialNetworkProject.utils.C3P0Util;
import zkSocialNetworkProject.utils.HibernateUtils;

public class CommAlbumDaoImp implements CommAlbumDao {

	@Override
	public void addAlbum(CommAlbum commAlbum) {
		// TODO Auto-generated method stub
		Session session = HibernateUtils.getCurSession();
		session.save(commAlbum);
	}

	@Override
	public void addAlbumPic(CommAlbumPic cap) {
		// TODO Auto-generated method stub
		Session session = HibernateUtils.getCurSession();
		session.save(cap);
	}

	@Override
	public List<CommAlbum> getCommAlbum(String uid) {
		// TODO Auto-generated method stub
		Session session = HibernateUtils.getCurSession();
		String hql = "from CommAlbum where uid = ?";
		Query query = session.createQuery(hql);
		query.setParameter(0, uid);
		List<CommAlbum> list = query.list();
		return list;
	}
	
	//弃用
	@Override
	public List<String> getCommAlbumPic(String cid) {
		// TODO Auto-generated method stub
		Session session = HibernateUtils.getCurSession();
		String hql = "select cphotoPath from CommAlbumPic where uid = ?";
		Query query = session.createQuery(hql);
		query.setParameter(0, cid);
		List<String> list = query.list();
		return list;
	}

	@Override
	public void deleteAlbum(String cid) {
		// TODO Auto-generated method stub
		Session session = HibernateUtils.getCurSession();
		String hql = "delete from CommAlbum where cid = ?";
		Query query = session.createQuery(hql);
		query.setParameter(0, cid);
		query.executeUpdate();
	}
	
	@Override
	public void deleteAlbumPic(String cid) {
		// TODO Auto-generated method stub
		Session session = HibernateUtils.getCurSession();
		String hql = "delete from CommAlbumPic where cid = ?";
		Query query = session.createQuery(hql);
		query.setParameter(0, cid);
		query.executeUpdate();
	}

	@Override
	public void deleteOnePhoto(String cphotoPath) {
		// TODO Auto-generated method stub
		Session session = HibernateUtils.getCurSession();
		String hql = "delete from CommAlbumPic where cphotoPath = ?";
		Query query = session.createQuery(hql);
		query.setParameter(0, cphotoPath);
		query.executeUpdate();
	}

	public Object idntity(shetuanUser memberstatus) throws Exception {
		QueryRunner runner =new QueryRunner(C3P0Util.getDataSource());
		String sql="update shtuan set status=? where memberstatus=?";
		runner.update(sql,"value",memberstatus);
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


}
