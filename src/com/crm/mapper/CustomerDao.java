package com.crm.mapper;

import java.util.List;

import com.crm.pojo.Customer;
import com.crm.pojo.QueryVo;

public interface CustomerDao {
	 //总条数
	public Integer customerCountByQueryVo(QueryVo vo);
	
	//结果集
	public List<Customer> selectCustomerListByQueryVo(QueryVo vo);
	
	//通过ID查询客户
	public Customer selectCustomerById(Integer id);
	
	//修改用户通过ID
	public void updateCustomerById(Customer customer);
	
	//通过Id删除客户
	public void deleteCustomerById(Integer id);
}
