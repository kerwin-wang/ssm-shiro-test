package com.kerwin.shiro.test.web.dao;

import com.kerwin.shiro.test.web.model.SysLog;
import java.util.List;

public interface SysLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysLog record);

    SysLog selectByPrimaryKey(Integer id);

    List<SysLog> selectAll();

    int updateByPrimaryKey(SysLog record);
}