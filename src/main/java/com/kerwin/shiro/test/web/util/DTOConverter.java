package com.kerwin.shiro.test.web.util;

import org.springframework.beans.BeanUtils;

/**
 * @ClassName: DTOConverter
 * @Description:
 * @version: v1.0.0
 * @Author: d.wang
 * @Date: 2019-04-12 10:53
 */
class DTOConverter
{
    static <T> Object convert(Object o, Class<T> t)
    {
        T t1 = null;
        try
        {
            t1 = t.newInstance();
        }
        catch (InstantiationException | IllegalAccessException e)
        {
            e.printStackTrace();
        }
        assert t1 != null;
        BeanUtils.copyProperties(o, t1);
        return t1;
    }
}
