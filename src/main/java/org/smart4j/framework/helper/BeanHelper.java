package org.smart4j.framework.helper;

import org.smart4j.framework.util.ReflectionUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 获取所有被框架管理的bean类，相当于一个Bean容器
 * 需要调用ClassHelper的getBeanClassSet方法
 * 循环调用ReflectionUtil类的newInstance方法，根据类来实例化对象
 * 将每次创建的对象存放在静态的Map中，可通过map的key获取对应的value（Bean对象）
 */
public final class BeanHelper {

    /**
     * 定义bean映射
     * 用来存放Bean类和Bean实例的映射关系
     */
    private static final Map<Class<?>, Object> BEAN_MAP = new HashMap<>();

    static{
        Set<Class<?>> beanClassSet = ClassHelper.getBeanClassSet();
        for (Class<?> beanClass: beanClassSet){
            Object obj = ReflectionUtil.newInstance(beanClass);
            BEAN_MAP.put(beanClass, obj);
        }
    }

    /**
     * 获取bean映射
     *
     * @return
     */
    public static Map<Class<?>, Object> getBeanMap(){
        return BEAN_MAP;
    }

    /**
     * 获取bean实例
     *
     * @param cls
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(Class<T> cls){
        if (!BEAN_MAP.containsKey(cls)){
            throw new RuntimeException("can not get bean by class:" + cls);
        }
        return (T) BEAN_MAP.get(cls);
    }
}
