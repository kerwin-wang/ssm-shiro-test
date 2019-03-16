package com.kerwin.shiro.test.web.dao;

import com.kerwin.shiro.test.web.model.SysPermModule;

public interface SysPermModuleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysPermModule record);

    int insertSelective(SysPermModule record);

    SysPermModule selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysPermModule record);

    int updateByPrimaryKey(SysPermModule record);
}