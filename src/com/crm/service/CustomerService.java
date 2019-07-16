package com.crm.service;

import com.crm.pojo.Customer;
import com.crm.pojo.QueryVo;
import com.utils.Page;

public interface CustomerService {
	
	//ͨ���ĸ����� ��ѯ��ҳ����
	public Page<Customer> selectPageByQueryVo(QueryVo vo);
	
	
	//ͨ��Id��ѯ�ͻ�
	public Customer selectCustomerById(Integer id);
	
	//�޸Ŀͻ�ͨ��ID
	public void updateCustomerById(Customer customer);
	
	//ͨ��IDɾ���ͻ�
	public void deleteCustomerById(Integer id);
}
