package com.junit;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.mapper.UserMapper;
import com.pojo.User;

public class Test1 {
	
	@Test
	public void testMapper() {
		//���غ��������ļ�
		String resource="sqlMapConfig.xml";
		InputStream in=Resources.getResourceAsStream(resource);
		//����SQLSessionFactory
		SqlSessionFactory sqlSessionFactory=new SqlSessionFactoryBuilder().build(in);
		//����sqlSession
		SqlSession sqlSession=sqlSessionFactory.openSession();
		
		//sqlsession����һ��ʵ����
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		User user = userMapper.findUserById(10);
	}
	
	@Test
	public void testMapperQueryVo() {
		//���غ��������ļ�
		String resource="sqlMapConfig.xml";
		InputStream in=Resources.getResourceAsStream(resource);
		//����sqlSessionFactory
		SqlSessionFactory sqlSessionFactory=new SqlSessionFactoryBuilder().build();
		//����Sqlsession
		SqlSession sqlSession=sqlSessionFactory.openSession();
		//sqlSession����һ��ʵ����
		UserMapper userMapper=sqlSession.getMapper(UserMapper);
		Integer i=userMapper.countUser();
		System.out.println(i);
	}
	
	
	
	
	
	
	
	
	
	
}
