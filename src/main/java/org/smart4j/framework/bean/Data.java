package org.smart4j.framework.bean;

/**
 * 返回数据对象
 * 封装Object类型数据，将对象写入HttpServletResponse对象中，输出至浏览器
 */
public class Data {

    /**
     * 模型数据
     */
    private Object model;

    public Object getModel() {
        return model;
    }

    public Data(Object model) {
        this.model = model;
    }
}
