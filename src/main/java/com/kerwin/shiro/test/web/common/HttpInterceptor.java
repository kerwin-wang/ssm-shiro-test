package com.kerwin.shiro.test.web.common;

import com.kerwin.shiro.test.web.model.SysPerm;
import com.kerwin.shiro.test.web.util.JsonMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @ClassName: HttpInterceptor
 * @Description:
 * @version: v1.0.0
 * @Author: d.wang
 * @Date: 2019-03-15 9:32
 */
@Slf4j
public class HttpInterceptor extends HandlerInterceptorAdapter
{

    private static final String START_TIME = "requestStartTime";

    private static final String END_TIME = "requestEndTime";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        String url = request.getRequestURL().toString();
        Map map = request.getParameterMap();
        log.info("request start url: {},paramMap: {}", url, JsonMapper.obj2String(map));
        Long start = System.currentTimeMillis();
        request.setAttribute(START_TIME,start);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception
    {
        String url = request.getRequestURL().toString();
        Map map = request.getParameterMap();
        log.info("request finish url: {},paramMap: {}", url, JsonMapper.obj2String(map));
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception
    {
        String url = request.getRequestURL().toString();
        Map map = request.getParameterMap();
        Long start = (Long) request.getAttribute(START_TIME);
        Long end = System.currentTimeMillis();
        log.info("request Completion url: {},paramMap: {},cost :{}", url, JsonMapper.obj2String(map),end - start);
    }
}
