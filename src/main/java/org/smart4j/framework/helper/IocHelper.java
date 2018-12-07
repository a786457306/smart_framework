package org.smart4j.framework.helper;

import org.smart4j.framework.annotation.Inject;
import org.smart4j.framework.util.ArrayUtil;
import org.smart4j.framework.util.CollectionUtil;
import org.smart4j.framework.util.ReflectionUtil;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * 依赖注入，注入Service成员变量
 * 控制反转：将控制权反转给框架。
 * 通过BeanHelper获取所有的Bean Map，
 * 遍历Map，取出Bean类与实例，并通过反射获取类中所有的成员变量
 * 若成员变量中有inject注解，就根据bean类获取类实例，注入到当前成员变量中
 * 用ReflectionUtil的setField方法修改成员变量的值
 */
public final class IocHelper {

    static{
        Map<Class<?>, Object> beanMap = BeanHelper.getBeanMap();
        if (CollectionUtil.isNotEmpty(beanMap)){
            for (Map.Entry<Class<?>, Object> beanEntry: beanMap.entrySet()){
                // 从BeanMap中获取Bean类与Bean实例
                Class<?> beanClass = beanEntry.getKey();
                Object beanInstance = beanEntry.getValue();
                // 获取Bean定义的所有成员变量 Bean Field
                Field[] beanFields = beanClass.getDeclaredFields();
                if (ArrayUtil.isNotEmpty(beanFields)){
                    // 遍历beanFields
                    for (Field beanField: beanFields){
                        // 获取所有需要注入的Service服务类并且创建实例
                        if (beanField.isAnnotationPresent(Inject.class)){
                            Class<?> beanFieldClass = beanField.getType();
                            Object beanFieldInstance = beanMap.get(beanFieldClass);
                            if (beanFieldInstance != null){
                                // 通过反射初始化BeanField的值
                                ReflectionUtil.setField(beanInstance, beanField, beanFieldInstance);
                            }
                        }
                    }
                }
            }
        }
    }
}
