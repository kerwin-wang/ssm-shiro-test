package com.kerwin.shiro.test.web.dao;

import com.kerwin.shiro.test.web.model.SysRolePerm;
import java.util.List;

public interface SysRolePermMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysRolePerm record);

    SysRolePerm selectByPrimaryKey(Integer id);

    List<SysRolePerm> selectAll();

    int updateByPrimaryKey(SysRolePerm record);
}