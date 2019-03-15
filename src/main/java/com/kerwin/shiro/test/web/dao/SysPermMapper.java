package com.kerwin.shiro.test.web.dao;

import com.kerwin.shiro.test.web.model.SysPerm;
import java.util.List;

public interface SysPermMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysPerm record);

    SysPerm selectByPrimaryKey(Integer id);

    List<SysPerm> selectAll();

    int updateByPrimaryKey(SysPerm record);
}