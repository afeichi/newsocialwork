package com.crm.mapper;

import java.util.List;

import com.crm.pojo.BaseDict;

public interface BaseDictDao {
//��ѯ
	public List<BaseDict> selectBaseDictListByCode(String code);
}
