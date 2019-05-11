package zkSocialNetworkProject.shetuan.service;
import zkSocialNetworkProject.domain.shetuanUser;
import zkSocialNetworkProject.shetuan.dao.UserDao;
import java.sql.SQLException;

import org.apache.catalina.User;

public class UserService {

	public boolean regist(shetuanUser user) {
		
		UserDao dao = new UserDao();
		int row = 0;
		try {
			row = dao.regist(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return row>0?true:false;
	}
	//激活
		public void active(String activeCode) {
			UserDao dao = new UserDao();
			try {
				dao.active(activeCode);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		
		//校验社团名字是否存在
		public boolean checkUsername(String username) {
			UserDao dao = new UserDao();
			Long isExist = 0L;
			try {
				isExist = dao.checkUsername(username);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return isExist>0?true:false;
		}
		
		
		//用户登录
		public User login(String username, String password) throws SQLException {
			UserDao dao = new UserDao();
			return dao.login(username,password);
		}
}