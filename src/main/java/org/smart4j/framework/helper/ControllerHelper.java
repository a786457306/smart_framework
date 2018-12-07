package org.smart4j.framework.helper;

import org.smart4j.framework.annotation.Action;
import org.smart4j.framework.bean.Handler;
import org.smart4j.framework.bean.Request;
import org.smart4j.framework.util.ArrayUtil;
import org.smart4j.framework.util.CollectionUtil;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 获取所有定义了Controller注解的类
 */
public final class ControllerHelper {

    /**
     * 存放请求与处理器的映射关系
     */
    private static final Map<Request, Handler> ACTION_MAP = new HashMap<>();

    static{
        // 获取所有Controller类
        Set<Class<?>> controllerClassSet = ClassHelper.getControllerClassSet();
        if (CollectionUtil.isNotEmpty(controllerClassSet)){
            // 遍历Controller类
            for (Class<?> controllerClass : controllerClassSet){
                Method[] methods = controllerClass.getDeclaredMethods();
                if (ArrayUtil.isNotEmpty(methods)){
                    for (Method method : methods){
                        if (method.isAnnotationPresent(Action.class)){
                            // 从Action注解中获取URL映射规则
                            Action action = method.getAnnotation(Action.class);//返回指定类型的注释
                            String mapping = action.value();
                            // 验证URL映射规则
                            if (mapping.matches("\\w+:/\\w*")){
                                String[] array = mapping.split(":");
                                if (ArrayUtil.isNotEmpty(array) && array.length == 2){
                                    String requestMethod = array[0];
                                    String requestPath = array[1];
                                    Request request = new Request(requestMethod, requestPath);
                                    Handler handler = new Handler(controllerClass, method);
                                    ACTION_MAP.put(request, handler);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * 获取Handler
     *
     * @param requestMehtod
     * @param requestPath
     * @return
     */
    public static Handler getHandler(String requestMehtod, String requestPath){
        Request request = new Request(requestMehtod, requestPath);
        return ACTION_MAP.get(request);
    }
}
