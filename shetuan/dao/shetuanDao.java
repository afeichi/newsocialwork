package zkSocialNetworkProject.shetuan.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import zkSocialNetworkProject.domain.shetuanUser;
import zkSocialNetworkProject.utils.C3P0Util;


public class shetuanDao {

	public void addUser(shetuanUser member) throws SQLException {
		//DataSourceUtils对应utils包下的DataSourceUtils.java
		//获得连接池中的连接对象
		QueryRunner runner = new QueryRunner(C3P0Util.getDataSource());
		//编写sql语句
		String sql = "insert into user values(?,?,?,?,?,?,?,?)";
		//调用update传递相应参数
		Object[] params= {member.getUserid(),member.getUsername(),member.getPassword(),member.getEmail(),member.getAdname(),member.getAdphone(),member.getEvident()};
		runner.update(sql,null,params);
		//如果做查询
		/*
		 * 第一个参数sql 比如：select * from user where nickname like ?;
		 * 第二个是将从数据库获得的数据封装到类型为User的BeanHandler中,多人多条数据BeanListHandler
		 * 第三个参数是查询条件，对应上面的sql语句则传  "%"+nickname+"%" ;
		 * %是不管nickname前后是否有其他字符，跟我们数据库学的一个意思
		 *   nickename是方法传入的参数 如：findUser(Stirng nickename)
		 runner.query(sql, new BeanHandler<User>(User.class),canshu);
		 */
		
	}

	public void deleteCom(String userid) throws SQLException {
		QueryRunner runner = new QueryRunner(C3P0Util.getDataSource());
		String sql = "delete from user where userid=?";
		runner.update(sql, userid);
	}

	public void updateUser(shetuanUser member) throws SQLException {
		QueryRunner runner = new QueryRunner(C3P0Util.getDataSource());
		String sql = "update user set sid=?,student_number=?,realname=?,id_number=?,sex=?,face_path=?,academy=?,profession=?,create_time=? where sid=?";
		Object[] params= {member.getUserid(),member.getUsername(),member.getPassword(),member.getEmail(),member.getAdname(),member.getAdphone(),member.getEvident()};
		runner.update(sql,null,params);
	}
	
	//查询所用用户的信息
	public List<shetuanUser> Queryall(String allInfo) throws SQLException {
		QueryRunner runner =new QueryRunner(C3P0Util.getDataSource());
		String sql="select * from user";
		return runner.query(sql, new BeanListHandler<shetuanUser>(shetuanUser.class));
	}
	
	//通过userid查询用户信息
	public shetuanUser QueryInfoByid(String userid) throws SQLException {
		QueryRunner runner = new QueryRunner(C3P0Util.getDataSource());
		String sql="select * from user where userid=?";
		return runner.query(sql, new BeanHandler<shetuanUser>(shetuanUser.class),userid);
	}
}
