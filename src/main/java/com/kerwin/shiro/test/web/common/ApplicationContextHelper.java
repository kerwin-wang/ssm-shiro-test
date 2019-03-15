package com.kerwin.shiro.test.web.common;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @ClassName: ApplicationContextHelper
 * @Description:
 * @version: v1.0.0
 * @Author: d.wang
 * @Date: 2019-03-14 16:23
 */
@Component("applicationContextHelper")
public class ApplicationContextHelper implements ApplicationContextAware
{
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException
    {
        applicationContext = context;
    }

    public static <T> T popBean(Class<T> clazz)
    {
        if (applicationContext == null)
        {
            return null;
        }
        return applicationContext.getBean(clazz);
    }

    public static <T> T popBean(String s, Class<T> clazz)
    {
        if (applicationContext == null)
        {
            return null;
        }
        return applicationContext.getBean(s, clazz);
    }
}
