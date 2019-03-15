package com.kerwin.shiro.test.web.validator;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.kerwin.shiro.test.web.exception.ParamException;
import org.apache.commons.collections.MapUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.*;

/**
 * @ClassName: ShiroTestValidator
 * @Description:
 * @version: v1.0.0
 * @Author: d.wang
 * @Date: 2019-03-13 15:55
 */
public class ShiroTestValidator
{
    private static ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();

    public static <T> Map<String,String> validate(T t,Class... groups){
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<T>> validateResult = validator.validate(t, groups);
        if (validateResult.isEmpty()){
            return Collections.emptyMap();
        }else {
            LinkedHashMap<String, String> errors = Maps.newLinkedHashMap();
            Iterator<ConstraintViolation<T>> iterator = validateResult.iterator();
            while (iterator.hasNext()){
                ConstraintViolation<T> violation = iterator.next();
                errors.put(violation.getPropertyPath().toString(),violation.getMessage());
            }
            return errors;
        }
    }

    public static Map<String,String> validate(Collection<?> collection){
        Preconditions.checkNotNull(collection);
        Iterator<?> iterator = collection.iterator();
        Map<String,String> errors;
        do{
            if (!iterator.hasNext()){
                return Collections.emptyMap();
            }
            Object object = iterator.next();
            errors = validate(object,new Class[0]);
        }while (errors.isEmpty());
        return errors;
    }

    public static Map<String,String> validateObject(Object object,Object... groups){
        if (groups != null && groups.length >0){
            return validate(Lists.asList(object,groups));
        }else {
            return validate(object,new Class[0]);
        }
    }

    public static void check(Object object){
        Map<String, String> map = ShiroTestValidator.validateObject(object);
        if (MapUtils.isNotEmpty(map)){
            throw new ParamException(map.toString());
        }
    }
}
