package com.situ.web.pojo.vo;

//vo:value object
public class GenderCountVO {
    private String name;
    private Integer value;

    public GenderCountVO() {
    }

    public GenderCountVO(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "GenderCountVO{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
