package com.kerwin.shiro.test.web.util;

import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.annotation.RequiredAnnotationBeanPostProcessor;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;

/**
 * @ClassName: Processor
 * @Description:
 * @version: v1.0.0
 * @Author: d.wang
 * @Date: 2019-03-12 11:52
 */
public class Processor
{
    AutowiredAnnotationBeanPostProcessor autowired = new AutowiredAnnotationBeanPostProcessor();

    CommonAnnotationBeanPostProcessor commonAnnotation = new CommonAnnotationBeanPostProcessor();

    RequiredAnnotationBeanPostProcessor requiredAnnotation = new RequiredAnnotationBeanPostProcessor();
}
