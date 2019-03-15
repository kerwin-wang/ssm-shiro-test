package com.kerwin.shiro.test.web.dao;

import com.kerwin.shiro.test.web.model.SysPermModule;
import java.util.List;

public interface SysPermModuleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysPermModule record);

    SysPermModule selectByPrimaryKey(Integer id);

    List<SysPermModule> selectAll();

    int updateByPrimaryKey(SysPermModule record);
}