package com.kerwin.shiro.test.web.exception;

/**
 * @ClassName: ParamException
 * @Description:
 * @version: v1.0.0
 * @Author: d.wang
 * @Date: 2019-03-15 9:52
 */
public class ParamException extends RuntimeException
{
    public ParamException()
    {
        super();
    }

    public ParamException(String message)
    {
        super(message);
    }

    public ParamException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public ParamException(Throwable cause)
    {
        super(cause);
    }

    protected ParamException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
