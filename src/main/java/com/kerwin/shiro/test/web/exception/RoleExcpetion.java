package com.kerwin.shiro.test.web.exception;

/**
 * @ClassName: RoleExcpetion
 * @Description:
 * @version: v1.0.0
 * @Author: d.wang
 * @Date: 2019-03-13 14:11
 */
public class RoleExcpetion extends RuntimeException
{
    public RoleExcpetion()
    {
        super();
    }

    public RoleExcpetion(String message)
    {
        super(message);
    }

    public RoleExcpetion(String message, Throwable cause)
    {
        super(message, cause);
    }

    public RoleExcpetion(Throwable cause)
    {
        super(cause);
    }

    protected RoleExcpetion(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
