package com.kerwin.shiro.test.web.service;

import com.kerwin.shiro.test.web.vo.UserVo;

/**
 * @ClassName: SysUserService
 * @Description:
 * @version: v1.0.0
 * @Author: d.wang
 * @Date: 2019-04-15 10:13
 */
public interface SysUserService
{
    void save(UserVo userVo);

    void update(UserVo userVo);
}
