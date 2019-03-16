package com.kerwin.shiro.test.web.service.impl;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.kerwin.shiro.test.web.dao.SysDeptMapper;
import com.kerwin.shiro.test.web.dto.DeptLevelDto;
import com.kerwin.shiro.test.web.model.SysDept;
import com.kerwin.shiro.test.web.service.SysTreeService;
import com.kerwin.shiro.test.web.util.DeptLevelUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.List;

/**
 * @ClassName: SysTreeServiceImpl
 * @Description:
 * @version: v1.0.0
 * @Author: d.wang
 * @Date: 2019-03-16 18:57
 */
@Service
public class SysTreeServiceImpl implements SysTreeService
{
    @Resource
    private SysDeptMapper sysDeptMapper;

    /**
     * @Description: 查询出部门树
     * @param
     * @Date: 2019-03-16 10:52
     */
    @Override
    public List<DeptLevelDto> deptTree(){
        List<SysDept> allDept = sysDeptMapper.getAllDept();
        List<DeptLevelDto> dtoList = Lists.newArrayList();
        for (SysDept dept : allDept)
        {
            DeptLevelDto dto = DeptLevelDto.adapt(dept);
            dtoList.add(dto);
        }

        return deptListToTree(dtoList);
    }

    private List<DeptLevelDto> deptListToTree(List<DeptLevelDto> dtoList)
    {
        if (CollectionUtils.isEmpty(dtoList)){
            return Lists.newArrayList();
        }
        //level -> [dept1,dept2,...]
        Multimap<String,DeptLevelDto> levelDtoMap = ArrayListMultimap.create();

        List<DeptLevelDto> rootList = Lists.newArrayList();
        for (DeptLevelDto dto : dtoList)
        {
            levelDtoMap.put(dto.getLevel(),dto);
            if (DeptLevelUtil.ROOT.equals(dto.getLevel())){
                rootList.add(dto);
            }
        }
        rootList.sort(deptSeqCompare());
        transformDeptTree(rootList,DeptLevelUtil.ROOT,levelDtoMap);
        return rootList;
    }

    private void transformDeptTree(List<DeptLevelDto> dtoList,String level,Multimap<String,DeptLevelDto> levelDtoMap){
        for (DeptLevelDto dto : dtoList)
        {
            //遍历该层的每一个元素
            //处理当前层级的数据
            String nextLevel = DeptLevelUtil.calculateLevel(level, dto.getId());
            //处理下一层
            List<DeptLevelDto> deptLevelDtoList = (List<DeptLevelDto>) levelDtoMap.get(nextLevel);

            if (CollectionUtils.isNotEmpty(deptLevelDtoList))
            {
                //排序
                deptLevelDtoList.sort(deptSeqCompare());
                //设置下一层部门
                dto.setDeptList(deptLevelDtoList);
                //进入到下一层处理
                transformDeptTree(deptLevelDtoList, nextLevel, levelDtoMap);
            }
        }
    }

    /**
     * @Description: 排序
     * @param
     * @Date: 2019-03-16 08:23
     */
    private Comparator<DeptLevelDto> deptSeqCompare()
    {
        return Comparator.comparingInt(SysDept::getSeq);
    }
}
