package com.kerwin.shiro.test.web.dao;

import com.kerwin.shiro.test.web.model.SysPerm;

public interface SysPermMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysPerm record);

    int insertSelective(SysPerm record);

    SysPerm selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysPerm record);

    int updateByPrimaryKey(SysPerm record);
}