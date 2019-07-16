package com.crm.mapper;

import java.util.List;

import com.crm.pojo.Customer;
import com.crm.pojo.QueryVo;

public interface CustomerDao {
	 //������
	public Integer customerCountByQueryVo(QueryVo vo);
	
	//�����
	public List<Customer> selectCustomerListByQueryVo(QueryVo vo);
	
	//ͨ��ID��ѯ�ͻ�
	public Customer selectCustomerById(Integer id);
	
	//�޸��û�ͨ��ID
	public void updateCustomerById(Customer customer);
	
	//ͨ��Idɾ���ͻ�
	public void deleteCustomerById(Integer id);
}
