package bingo.service;

import bingo.dao.UserDao;
import bingo.domain.User;

public class UserService {
	
	private UserDao userDao;
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public User getUserByCodeName(User u) {
		User user = userDao.getUserByCodeName(u.getUser_code());
		if(user == null) {
			throw new RuntimeException("用户名输入错误！");
		};
		if(user.getUser_password() != u.getUser_password()) {
			throw new RuntimeException("密码错误！");
		}
		return user;
	}
}
