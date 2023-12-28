package com.rose.common.utils;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class ObjUtils {
    public static Map<String,Object> ObjToMap(Object obj){
        Map<String,Object> map=new HashMap<String, Object>();
        Field[] fields = obj.getClass().getDeclaredFields();
        for(Field field:fields){
            if(field.getName().equalsIgnoreCase("class")){
                continue;
            }
            field.setAccessible(true);
            try {
                map.put(field.getName(), field.get(obj));
            } catch (IllegalAccessException e) {
                log.error("属性获取异常",e);
            }
        }
        return map;
    }

    public static Map<byte[],byte[]> ObjToByteMap(Object obj){
        Map<byte[],byte[]> map=new HashMap<byte[],byte[]>();
        Field[] fields = obj.getClass().getDeclaredFields();
        for(Field field:fields){
            if(field.getName().equalsIgnoreCase("class")){
                continue;
            }
            field.setAccessible(true);
            try {
                map.put(field.getName().getBytes(), field.get(obj).toString().getBytes());
            } catch (IllegalAccessException e) {
                log.error("属性获取异常",e);
            }
        }
        return map;
    }
    public Object mapToObj(Map<String,Object> map,Class<?> clz) throws Exception{
        Object obj = clz.newInstance();
        Field[] declaredFields = obj.getClass().getDeclaredFields();
        for(Field field:declaredFields){
            int mod = field.getModifiers();
            if(Modifier.isStatic(mod) || Modifier.isFinal(mod)){
                continue;
            }
            field.setAccessible(true);
            field.set(obj, map.get(field.getName()));
        }
        return obj;
    }
}
