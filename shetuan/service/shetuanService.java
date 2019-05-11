package zkSocialNetworkProject.shetuan.service;

import java.sql.SQLException;
import java.util.List;

import zkSocialNetworkProject.domain.shetuanUser;
import zkSocialNetworkProject.shetuan.dao.shetuanDao;

public class shetuanService {
	shetuanDao dao = new shetuanDao();
	public boolean addUser(shetuanUser user) {
		try {
			dao.addUser(user);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
	}

	public boolean deleteCom(String sid) {
		try {
			dao.deleteCom(sid);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}



	public boolean updateUser(shetuanUser user) {
		try {
			dao.updateUser(user);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
	}

	//查询所有用户
	public List<shetuanUser> Queryall(String allInfo) {
		try {
			return dao.Queryall(allInfo);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	//查询userid的用户信息
	public shetuanUser QueryInfoByid(String sid) {
		try {
			return dao.QueryInfoByid(sid);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
