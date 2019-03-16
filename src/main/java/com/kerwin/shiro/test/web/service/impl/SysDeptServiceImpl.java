package com.kerwin.shiro.test.web.service.impl;

import com.kerwin.shiro.test.web.dao.SysDeptMapper;
import com.kerwin.shiro.test.web.exception.ParamException;
import com.kerwin.shiro.test.web.model.SysDept;
import com.kerwin.shiro.test.web.service.SysDeptService;
import com.kerwin.shiro.test.web.util.DeptLevelUtil;
import com.kerwin.shiro.test.web.validator.ShiroTestValidator;
import com.kerwin.shiro.test.web.vo.DeptVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @ClassName: SysDeptServiceImpl
 * @Description:
 * @version: v1.0.0
 * @Author: d.wang
 * @Date: 2019-03-16 17:37
 */
@Service
public class SysDeptServiceImpl implements SysDeptService
{
    @Resource
    private SysDeptMapper sysDeptMapper;

    @Override
    public void saveDept(DeptVo deptVo)
    {
        ShiroTestValidator.check(deptVo);
        if (checkExist(deptVo.getParentId(), deptVo.getName(), deptVo.getId()))
        {
            throw new ParamException("同一层级下存在相同名称的部门");
        }
        SysDept dept = SysDept.builder().name(deptVo.getName()).parentId(deptVo.getParentId()).seq(deptVo.getSeq()).remark(deptVo.getRemark())
                .build();
        dept.setLevel(DeptLevelUtil.calculateLevel(getLevel(deptVo.getParentId()), deptVo.getParentId()));
        dept.setOperator("system");//TODO
        dept.setOperateIp("127.0.0.1");//TODO
        dept.setOperateTime(new Date());
        sysDeptMapper.insert(dept);
    }

    /**
     * @Description: 检查部门是否已存在
     * @param parentId
     * @param deptName
     * @param deptId
     * @Date: 2019-03-16 06:34
     */
    public boolean checkExist(Integer parentId, String deptName, Integer deptId)
    {
        //todo
        return true;
    }

    /**
     * @Description: 获取level
     * @param deptId
     * @Date: 2019-03-16 06:28
     */
    private String getLevel(Integer deptId)
    {
        SysDept sysDept = sysDeptMapper.selectByPrimaryKey(deptId);
        if (sysDept == null)
        {
            return null;
        }
        return sysDept.getLevel();
    }
}
