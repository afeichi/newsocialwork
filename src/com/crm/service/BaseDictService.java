package com.crm.service;

import java.util.List;

import com.crm.pojo.BaseDict;

public interface BaseDictService {
	
	
	//��ѯ
	public List<BaseDict> selectBaseDictListByCode(String code);
}
