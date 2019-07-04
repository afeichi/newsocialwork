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
		//加载核心配置文件
		String resource="sqlMapConfig.xml";
		InputStream in=Resources.getResourceAsStream(resource);
		//创建SQLSessionFactory
		SqlSessionFactory sqlSessionFactory=new SqlSessionFactoryBuilder().build(in);
		//创建sqlSession
		SqlSession sqlSession=sqlSessionFactory.openSession();
		
		//sqlsession生成一个实现类
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		User user = userMapper.findUserById(10);
	}
	
	@Test
	public void testMapperQueryVo() {
		//加载核心配置文件
		String resource="sqlMapConfig.xml";
		InputStream in=Resources.getResourceAsStream(resource);
		//创建sqlSessionFactory
		SqlSessionFactory sqlSessionFactory=new SqlSessionFactoryBuilder().build();
		//创建Sqlsession
		SqlSession sqlSession=sqlSessionFactory.openSession();
		//sqlSession生成一个实现类
		UserMapper userMapper=sqlSession.getMapper(UserMapper);
		Integer i=userMapper.countUser();
		System.out.println(i);
	}
	
	
	
	
	
	
	
	
	
	
}
