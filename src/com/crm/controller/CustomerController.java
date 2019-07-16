package com.crm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crm.pojo.BaseDict;
import com.crm.pojo.Customer;
import com.crm.pojo.QueryVo;
import com.crm.service.BaseDictService;
import com.crm.service.CustomerService;
import com.utils.Page;

@Controller
public class CustomerController {
	
	@Autowired
	private BaseDictService baseDictService;
	@Autowired
	private CustomerService customerService;
	
	//注解在成员变量上面
	@Value("${fromType.code")
	private String fromTypeCode;
	
	
	
	//入口
	@RequestMapping(value="/Customer/list.action")
	public String list(QueryVo vo,Model model) {
		List<BaseDict> fromType =baseDictService.selectBaseDictListByCode(fromTypeCode);
		List<BaseDict> industryType=baseDictService.selectBaseDictListByCode("001");
		List<BaseDict> levelType =baseDictService.selectBaseDictListByCode("006");
		model.addAttribute("fromType", fromType);
		model.addAttribute("industryType", industryType);
		model.addAttribute("levelType",levelType);
		
		//通过四个条件 查询分页对象
		Page<Customer> page =customerService.selectPageByQueryVo(vo);
		model.addAttribute("page", page);
		model.addAttribute("custName", vo.getCustName());
		model.addAttribute("custSource", vo.getCustSource());
		model.addAttribute("custIndustry", vo.getCustIndustry());
		model.addAttribute("custLevel", vo.getCustLevel());
		
		return "customer";
	}
	//修改页面
	@RequestMapping(value="/customer/edit.action",method=RequestMethod.GET)
	public @ResponseBody
	Customer edit(Integer id) {
		System.out.println("进来了");
		return customerService.selectCustomerById(id);
	}
	
	
	//修改保存
	@RequestMapping(value="/customer/update.action")
	public @ResponseBody
	String update(Customer customer) {
		//修改
		customerService.updateCustomerById(customer);
		System.out.println("我也进来了");
		return "OK";
	}
	
	//删除
	@RequestMapping(value="/customer/delete.action")
	public @ResponseBody
	String delete(Integer id) {
		//删除
		customerService.deleteCustomerById(id);
		System.out.println("我删除了");
		return "ok";
	}
}
