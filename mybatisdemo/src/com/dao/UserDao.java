package com.dao;

import com.pojo.User;

public interface UserDao {
	public User selectUserById(Integer id);
}
