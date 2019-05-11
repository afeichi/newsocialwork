package zkSocialNetworkProject.shetuan.dao;
import java.sql.SQLException;

import org.apache.catalina.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import zkSocialNetworkProject.domain.shetuanUser;
import zkSocialNetworkProject.utils.C3P0Util;
public class UserDao {

	public int regist(shetuanUser user) throws SQLException {
		QueryRunner runner = new QueryRunner(C3P0Util.getDataSource());
		String sql = "insert into shetuan values(?,?,?,?,?,?,?,?)";
		int update=runner.update(sql, user.getUserid(),user.getUsername(),user.getPassword(),user.getEmail(),user.getAdname(),user.getAdphone(),user.getEvident());
		return update;
	}
	
		//激活
		public void active(String activeCode) throws SQLException {
			QueryRunner runner = new QueryRunner(C3P0Util.getDataSource());
			String sql = "update user set state=? where code=?";
			runner.update(sql, 1,activeCode);
		}

		//校验社团名字是否存在
		public Long checkUsername(String username) throws SQLException {
			QueryRunner runner = new QueryRunner(C3P0Util.getDataSource());
			String sql = "select count(*) from user where username=?";
			Long query = (Long) runner.query(sql, new ScalarHandler(), username);
			return query;
	}
				//用户登录
		public User login(String username, String password) throws SQLException {
			
			QueryRunner runner = new QueryRunner(C3P0Util.getDataSource());
			String sql = "select * from user where username=? and password=?";
			return runner.query(sql, new BeanHandler<User>(User.class), username,password);
			
		}

}
