package com.mapper;

import java.util.List;

import com.pojo.QueryVo;
import com.pojo.User;

public class UserMapper {
  public User findUserById(Integer id);
  public List<User> findUserByQueryVo(QueryVo vo) ;
  
  
  //查询数据条数
  public Integer countUser();
//	根据性别和名字查询用户
   	public List<User> selectUserBySexAndUsername(User user);
	//根据多个id查询用户信息  
	public List<User> selectUserByIds(List<Integer> ids); 
}
