package com.kerwin.shiro.test.web.service;

import com.kerwin.shiro.test.web.dto.DeptLevelDto;

import java.util.List;

/**
 * @ClassName: SysTreeService
 * @Description:
 * @version: v1.0.0
 * @Author: d.wang
 * @Date: 2019-03-16 18:56
 */
public interface SysTreeService
{
    /**
     * @Description: 查询出部门树
     * @param
     * @Date: 2019-03-16 10:52
     */
    public List<DeptLevelDto> deptTree();
}
