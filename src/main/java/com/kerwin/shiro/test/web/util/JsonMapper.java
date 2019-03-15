package com.kerwin.shiro.test.web.util;

import com.sun.org.apache.bcel.internal.generic.NEW;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.ser.impl.SimpleFilterProvider;
import org.codehaus.jackson.type.TypeReference;


/**
 * @ClassName: JsonMapper
 * @Description:
 * @version: v1.0.0
 * @Author: d.wang
 * @Date: 2019-03-14 15:56
 */
@Slf4j
@SuppressWarnings("unchecked")
public class JsonMapper
{
    private static ObjectMapper objectMapper = new ObjectMapper();

    static
    {
        objectMapper.disable(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.setFilters(new SimpleFilterProvider().setFailOnUnknownId(false));
        objectMapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_EMPTY);

    }

    public static <T> String obj2String(T t)
    {
        if (t == null)
        {
            return null;
        }
        try
        {
            return t instanceof String ? (String) t : objectMapper.writeValueAsString(t);
        }
        catch (Exception e)
        {
            log.warn("parse object to string exception,error:{}",e);
            return null;
        }
    }

    public static <T> T string2Obj(String s, TypeReference<T> typeReference)
    {
        if (s == null || typeReference == null)
        {
            return null;
        }
        try
        {
            return (T)(typeReference.getType().equals(String.class) ? s :objectMapper.readValue(s,typeReference));
        }catch (Exception e){
            log.warn("parse string to object exception,string:{},TypeReference<T>:{},error:{}",s,typeReference,e);
            return null;
        }
    }
}
