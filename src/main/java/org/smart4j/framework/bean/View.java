package org.smart4j.framework.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * 处理返回值为View类型的视图对象，是jsp页面
 */
public class View {

    /**
     * 视图路径
     */
    private String path;

    /**
     * 模型数据
     */
    private Map<String, Object> model;

    public Map<String, Object> getModel() {
        return model;
    }

    public String getPath() {
        return path;
    }

    public View(String path) {
        this.path = path;
        model = new HashMap<>();
    }

    public View addModel(String key, Object value){
        model.put(key, value);
        return this;
    }
}
