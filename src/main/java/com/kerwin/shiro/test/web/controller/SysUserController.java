package com.kerwin.shiro.test.web.controller;

import com.kerwin.shiro.test.web.common.JsonData;
import com.kerwin.shiro.test.web.service.SysUserService;
import com.kerwin.shiro.test.web.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName: SysUserController
 * @Description:
 * @version: v1.0.0
 * @Author: d.wang
 * @Date: 2019-04-15 11:01
 */
@Controller
@Slf4j
@RequestMapping("/sys/user")
public class SysUserController
{
    @Autowired
    private SysUserService sysUserService;

    @ResponseBody
    @RequestMapping("/save")
    public JsonData saveSysDept(UserVo userVo){
        sysUserService.save(userVo);
        return JsonData.success();
    }

    @ResponseBody
    @RequestMapping("/update")
    public JsonData updateDept(UserVo userVo){
        sysUserService.update(userVo);
        return JsonData.success();
    }
}
