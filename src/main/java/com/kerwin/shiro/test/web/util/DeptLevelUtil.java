package com.kerwin.shiro.test.web.util;

import org.apache.commons.lang3.StringUtils;

/**
 * @ClassName: DeptLevelUtil
 * @Description: 处理部门层级工具类
 * @version: v1.0.0
 * @Author: d.wang
 * @Date: 2019-03-16 17:58
 */
public class DeptLevelUtil
{
    public static final String SEPARATOR = ".";

    public static final String ROOT = "0";

    /**
     * @Description: example：0,0.1,0.1.1
     * @param parentLevel 父层层级
     * @param parentId 父层id
     * @Date: 2019-03-16 06:05
     */
    public static String calculateLevel(String parentLevel, int parentId)
    {
        if (StringUtils.isBlank(parentLevel)){
            return ROOT;
        }else {
            return StringUtils.join(parentLevel,SEPARATOR,parentId);
        }
    }
}
