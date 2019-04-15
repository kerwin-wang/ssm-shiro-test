package com.kerwin.shiro.test.web.service.impl;

import com.google.common.base.Preconditions;
import com.kerwin.shiro.test.web.dao.SysUserMapper;
import com.kerwin.shiro.test.web.exception.ParamException;
import com.kerwin.shiro.test.web.model.SysUser;
import com.kerwin.shiro.test.web.service.SysUserService;
import com.kerwin.shiro.test.web.util.MD5Util;
import com.kerwin.shiro.test.web.util.PasswordUtil;
import com.kerwin.shiro.test.web.validator.BeanValidator;
import com.kerwin.shiro.test.web.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @ClassName: SysUserServiceImpl
 * @Description:
 * @version: v1.0.0
 * @Author: d.wang
 * @Date: 2019-04-15 10:14
 */
@Service
public class SysUserServiceImpl implements SysUserService
{
    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public void save(UserVo userVo)
    {
        BeanValidator.check(userVo);
        if (checkTelephoneExist(userVo.getTelephone(), userVo.getId()))
        {
            throw new ParamException("电话已被占用");
        }
        if (checkEmailExist(userVo.getMail(), userVo.getId()))
        {
            throw new ParamException("邮箱以备占用");
        }
        String password = PasswordUtil.generatePassword();
        password = "123456";
        String encryptedPassword = MD5Util.encrypt(password);
        SysUser sysUser = SysUser.builder().userName(userVo.getUserName()).telephone(userVo.getTelephone()).mail(userVo.getMail())
                .password(encryptedPassword).deptId(userVo.getDeptId()).status(userVo.getStatus()).remark(userVo.getRemark()).build();
        sysUser.setOperator("system");//TODO
        sysUser.setOperateIp("127.0.0.1");//TODO
        sysUser.setOperateTime(new Date());

        // TODO send Email
        sysUserMapper.insertSelective(sysUser);

    }

    public void update(UserVo userVo)
    {
        BeanValidator.check(userVo);
        if (checkTelephoneExist(userVo.getTelephone(), userVo.getId()))
        {
            throw new ParamException("电话已被占用");
        }
        if (checkEmailExist(userVo.getMail(), userVo.getId()))
        {
            throw new ParamException("邮箱以备占用");
        }
        SysUser before = sysUserMapper.selectByPrimaryKey(userVo.getId());
        Preconditions.checkNotNull(before, "待更新的用户不存在");
        SysUser after = SysUser.builder().id(userVo.getId()).userName(userVo.getUserName()).telephone(userVo.getTelephone()).mail(userVo.getMail())
                .password(before.getPassword()).deptId(userVo.getDeptId()).status(userVo.getStatus()).remark(userVo.getRemark()).build();
        sysUserMapper.updateByPrimaryKeySelective(after);
    }

    private boolean checkEmailExist(String email, Integer userId)
    {
        return false;
    }

    private boolean checkTelephoneExist(String telephone, Integer userId)
    {
        return false;
    }
}
