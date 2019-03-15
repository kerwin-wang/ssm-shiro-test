package com.kerwin.shiro.test.web.dao;

import com.kerwin.shiro.test.web.model.SysDept;
import java.util.List;

public interface SysDeptMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysDept record);

    SysDept selectByPrimaryKey(Integer id);

    List<SysDept> selectAll();

    int updateByPrimaryKey(SysDept record);
}