package org.smart4j.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 通过工具类封装Java反射相关的API
 */
public class ReflectionUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReflectionUtil.class);

    /**
     * 创建实例
     *
     * @param cls
     * @return
     */
    public static Object newInstance(Class<?> cls) {
        Object instance;
        try{
            instance = cls.newInstance();// 规定类型后直接用这个方法就能创建相同类型的实例
        } catch (Exception e) {
            LOGGER.error("new instance failure", e);
            throw new RuntimeException(e);
        }
        return instance;
    }

    /**
     * 调用方法
     *
     * @param obj
     * @param method
     * @param args
     * @return
     */
    public static Object invokeMethod(Object obj, Method method, Object... args){
        Object result;
        try {
            // 将此对象的 accessible 标志设置为指示的布尔值。值为 true 则指示反射的对象
            // 在使用时应该取消 Java 语言访问检查。值为 false 则指示反射的对象应该实施Java 语言访问检查。
            method.setAccessible(true);
            result = method.invoke(obj, args);
        } catch (Exception e){
            LOGGER.error("invoke method failure", e);
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * 设置成员变量的值
     *
     * @param obj
     * @param field
     * @param value
     */
    public static void setField(Object obj, Field field, Object value){
        try {
            field.setAccessible(true);
            // 为变量赋新值
            field.set(obj, value);
        } catch (Exception e){
            LOGGER.error("set field failure", e);
            throw new RuntimeException(e);
        }
    }
}
