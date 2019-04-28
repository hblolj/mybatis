package com.hblolj.bean;

import lombok.Data;

/**
 * @author: hblolj
 * @Date: 2019/4/22 19:35
 * @Description:
 * @Version:
 **/
@Data
public class Student {

    private Integer studentId;

    private String name;

    private Integer age;

    private String sex;

    public Student() {
    }

    public Student(Integer studentId, String name, Integer age, String sex) {
        this.studentId = studentId;
        this.name = name;
        this.age = age;
        this.sex = sex;
    }
}
