package com.kerwin.shiro.test.web.service.impl;

import com.google.common.base.Preconditions;
import com.kerwin.shiro.test.web.dao.SysDeptMapper;
import com.kerwin.shiro.test.web.exception.ParamException;
import com.kerwin.shiro.test.web.model.SysDept;
import com.kerwin.shiro.test.web.service.SysDeptService;
import com.kerwin.shiro.test.web.util.DeptLevelUtil;
import com.kerwin.shiro.test.web.validator.ShiroTestValidator;
import com.kerwin.shiro.test.web.vo.DeptVo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

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
        if (checkExist(deptVo.getParentId(), deptVo.getName(), null))
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

    @Override
    public void updateDept(DeptVo deptVo)
    {
        ShiroTestValidator.check(deptVo);
        if (checkExist(deptVo.getParentId(),deptVo.getName(),deptVo.getId())){
            throw new ParamException("同一层级下存在相同名称的部门");
        }

        SysDept before = sysDeptMapper.selectByPrimaryKey(deptVo.getId());
        Preconditions.checkNotNull(before, "待更新的部门不存在");

        SysDept after = SysDept.builder().id(deptVo.getId()).name(deptVo.getName()).parentId(deptVo.getParentId()).seq(deptVo.getSeq())
                .remark(deptVo.getRemark()).build();
        after.setLevel(DeptLevelUtil.calculateLevel(getLevel(deptVo.getParentId()),deptVo.getParentId()));
        after.setOperator("system-update");//TODO
        after.setOperateIp("127.0.0.1");//TODO
        after.setOperateTime(new Date());

        //更新部门，和部门下的子部门
        updateWithChild(before,after);
    }

    /**
     * @Description: 更新部门，和部门下的子部门，作为一个事务，同时更新
     * @param before
     * @param after
     * @Date: 2019-03-18 11:44
     */
    @Transactional
    protected void updateWithChild(SysDept before, SysDept after){
        String newLevelPrefix = after.getLevel();
        String oldLevelPrefix = before.getLevel();
        if (!newLevelPrefix.equals(oldLevelPrefix)){
            //取出当前level部门的所有子部门
            List<SysDept> childDeptList = sysDeptMapper.getChildDeptListByLevel(oldLevelPrefix);
            if (CollectionUtils.isNotEmpty(childDeptList)){
                for (SysDept sysDept : childDeptList)
                {
                    String level = sysDept.getLevel();
                    if (level.indexOf(oldLevelPrefix) == 0){
                        level = newLevelPrefix + level.substring(oldLevelPrefix.length());
                        sysDept.setLevel(level);
                    }
                }
                sysDeptMapper.batchUpdateLevel(childDeptList);
            }
        }
        sysDeptMapper.updateByPrimaryKey(after);
    }

    /**
     * @Description: 检查部门是否已存在
     * @param parentId
     * @param deptName
     * @param deptId
     * @Date: 2019-03-16 06:34
     */
    private boolean checkExist(Integer parentId, String deptName, Integer deptId)
    {
        return sysDeptMapper.countByNameAndParentId(parentId,deptName,deptId) > 0;
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
