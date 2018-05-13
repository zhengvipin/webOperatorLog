package com.springboot.domain;

/**
 * @classDesc: 操作对象封装类
 * @author: Vipin Zheng
 * @createDate: 2018-05-13 12:36:44
 * @version: v1.0
 */
public class Change {
    private String propName;        // 具体属性名
    private Object beValue;         // 编辑前属性值
    private Object afValue;         // 编辑后属性值

    public Change() {
    }

    public Change(String propName, Object beValue, Object afValue) {
        this.propName = propName;
        this.beValue = beValue;
        this.afValue = afValue;
    }

    public String getPropName() {
        return propName;
    }

    public void setPropName(String propName) {
        this.propName = propName;
    }

    public Object getBeValue() {
        return beValue;
    }

    public void setBeValue(Object beValue) {
        this.beValue = beValue;
    }

    public Object getAfValue() {
        return afValue;
    }

    public void setAfValue(Object afValue) {
        this.afValue = afValue;
    }

    @Override
    public String toString() {
        return "Change{" +
                "propName='" + propName + '\'' +
                ", beValue=" + beValue +
                ", afValue=" + afValue +
                '}';
    }
}
