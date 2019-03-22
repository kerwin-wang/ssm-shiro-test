package com.kerwin.shiro.test.web.controller;

import com.kerwin.shiro.test.web.common.JsonData;
import com.kerwin.shiro.test.web.dto.DeptLevelDto;
import com.kerwin.shiro.test.web.service.SysDeptService;
import com.kerwin.shiro.test.web.service.SysTreeService;
import com.kerwin.shiro.test.web.vo.DeptVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName: DeptController
 * @Description:
 * @version: v1.0.0
 * @Author: d.wang
 * @Date: 2019-03-16 17:18
 */
@Controller
@Slf4j
@RequestMapping("/sys/dept")
public class SysDeptController
{
    @Resource
    private SysDeptService sysDeptServiceImpl;

    @Resource
    private SysTreeService sysTreeServiceImpl;


    @ResponseBody
    @RequestMapping("/saveDept")
    public JsonData saveSysDept(DeptVo deptVo){
        sysDeptServiceImpl.saveDept(deptVo);
        return JsonData.success();
    }

    @RequestMapping("/updateDept")
    @ResponseBody
    public JsonData updateDept(DeptVo deptVo){
        sysDeptServiceImpl.updateDept(deptVo);
        return JsonData.success();
    }

    @RequestMapping("/tree")
    @ResponseBody
    public JsonData tree(){
        List<DeptLevelDto> dtoList = sysTreeServiceImpl.deptTree();
        return JsonData.success(dtoList);
    }
}
