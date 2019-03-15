package com.kerwin.shiro.test.web.dao;

import com.kerwin.shiro.test.web.model.SysRoleUser;
import java.util.List;

public interface SysRoleUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysRoleUser record);

    SysRoleUser selectByPrimaryKey(Integer id);

    List<SysRoleUser> selectAll();

    int updateByPrimaryKey(SysRoleUser record);
}