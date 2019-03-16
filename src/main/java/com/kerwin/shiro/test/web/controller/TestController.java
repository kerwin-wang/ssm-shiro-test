package com.kerwin.shiro.test.web.controller;

import com.kerwin.shiro.test.web.common.ApplicationContextHelper;
import com.kerwin.shiro.test.web.common.JsonData;
import com.kerwin.shiro.test.web.dao.SysPermMapper;
import com.kerwin.shiro.test.web.model.SysPerm;
import com.kerwin.shiro.test.web.util.JsonMapper;
import com.kerwin.shiro.test.web.validator.ShiroTestValidator;
import com.kerwin.shiro.test.web.vo.TestVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @ClassName: TestController
 * @Description:
 * @version: v1.0.0
 * @Author: d.wang
 * @Date: 2019-03-13 9:35
 */
@Controller
@RequestMapping("/test")
@Slf4j
public class TestController
{
    @RequestMapping("/hello")
    @ResponseBody
    public String hello(){
        log.info("hello");
        throw new RuntimeException();
    }

    @RequestMapping("/testValidator")
    @ResponseBody
    public JsonData testValidator(TestVo testVo){
        log.info("test validator");
        try
        {
            Map<String, String> map = ShiroTestValidator.validateObject(testVo);
            if (map != null && map.entrySet().size() >0 ){
                for (Map.Entry<String, String> stringEntry : map.entrySet())
                {
                    log.info("{} -> {}",stringEntry.getKey(),stringEntry.getValue());
                }
            }
        }catch (Exception e){

        }
        return JsonData.success("test validator");
    }

    @RequestMapping("testApplicationContext")
    @ResponseBody
    public JsonData testApplicationContext(TestVo vo){
        log.info("test applicationContextHelper");
        SysPermMapper sysPermMapper = ApplicationContextHelper.popBean(SysPermMapper.class);
        SysPerm sysPerm = sysPermMapper.selectByPrimaryKey(1);
        log.info(JsonMapper.obj2String(sysPerm));
        ShiroTestValidator.check(vo);
        return JsonData.success("OK");

    }
}
