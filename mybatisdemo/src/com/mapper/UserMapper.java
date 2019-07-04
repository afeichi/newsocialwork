package com.mapper;

import java.util.List;

import com.pojo.QueryVo;
import com.pojo.User;

public class UserMapper {
  public User findUserById(Integer id);
  public List<User> findUserByQueryVo(QueryVo vo) ;
  
  
  //��ѯ��������
  public Integer countUser();
//	�����Ա�����ֲ�ѯ�û�
   	public List<User> selectUserBySexAndUsername(User user);
	//���ݶ��id��ѯ�û���Ϣ  
	public List<User> selectUserByIds(List<Integer> ids); 
}
