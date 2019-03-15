package com.kerwin.shiro.test.web.common;

import com.kerwin.shiro.test.web.exception.ParamException;
import com.kerwin.shiro.test.web.exception.RoleExcpetion;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: ShiroTestExceptionResolver
 * @Description:
 * @version: v1.0.0
 * @Author: d.wang
 * @Date: 2019-03-13 14:14
 */
@Slf4j
public class ShiroTestExceptionResolver implements HandlerExceptionResolver
{
    @Autowired
    private MappingJackson2JsonView jsonView;

    @Override
    public ModelAndView resolveException(HttpServletRequest request,
            HttpServletResponse response, Object o, Exception e)
    {

        String url = request.getRequestURL().toString();
        ModelAndView mv;
        String defaultError = "system error";
        if (e instanceof RoleExcpetion || e instanceof ParamException){

            log.info("param or role exception [{}]",e.getMessage());
            JsonData result = JsonData.fail(e.getMessage());
            mv = new ModelAndView(jsonView,result.toMap());
        }else {
            log.info("系统内部异常 [{}]",defaultError);
            JsonData result = JsonData.fail(defaultError);
            mv = new ModelAndView(jsonView,result.toMap());
        }
        return mv;
    }
}
