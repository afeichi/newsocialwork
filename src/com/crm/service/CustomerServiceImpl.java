package com.crm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.mapper.CustomerDao;
import com.crm.pojo.Customer;
import com.crm.pojo.QueryVo;
import com.utils.Page;
@Service
//�ͻ�����
public class CustomerServiceImpl implements CustomerService {
	@Autowired
		private CustomerDao customerDao;
		//ͨ���ĸ����� ��ѯ��ҳ����
		public Page<Customer> selectPageByQueryQueryVo(QueryVo vo){
			Page<Customer> page=new Page<Customer>();
			//ÿҳ��
			page.setSize(5);
			vo.setSize(5);
			if(null!=vo) {
				//�жϵ�ǰҳ
				if(null!=vo.getPage()) {
					page.setPage(vo.getPage());
					vo.setStartRow((vo.getPage()-1)*vo.getSize());
				}
				if(null!=vo.getCustName()&&!"".equals(vo.getCustName().trim())) {
					vo.setCustName(vo.getCustName().trim());
				}
				if(null!=vo.getCustSource()&&!"".equals(vo.getCustSource().trim())) {
					vo.setCustSource(vo.getCustSource().trim());
				}
				if(null != vo.getCustIndustry() && !"".equals(vo.getCustIndustry().trim())){
					vo.setCustIndustry(vo.getCustIndustry().trim());
				}
				if(null != vo.getCustLevel() && !"".equals(vo.getCustLevel().trim())){
					vo.setCustLevel(vo.getCustLevel().trim());
				}
				//������
				page.setTotal(customerDao.customerCountByQueryVo(vo));
				page.setRows(customerDao.selectCustomerListByQueryVo(vo));
			}
			return page;
			
		}
		
		

		
		//ͨ��IDɾ���ͻ�
		
		public void deleteCustomerById(Integer id) {
			customerDao.deleteCustomerById(id);
		}


		@Override
		public Page<Customer> selectPageByQueryVo(QueryVo vo) {
			// TODO Auto-generated method stub
			return null;
		}


		@Override
		public void updateCustomerById(Customer customer) {
			// TODO Auto-generated method stub
			
		}




		@Override
		public Customer selectCustomerById(Integer id) {
			// TODO Auto-generated method stub
			return null;
		}
			
}
