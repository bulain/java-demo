package com.bulain.hessian.pojo;

import java.io.Serializable;
import java.util.Date;

public class Demo implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String name;
    private Integer age;
    private Date createAt;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
    public Date getCreateAt() {
        return createAt;
    }
    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

}
