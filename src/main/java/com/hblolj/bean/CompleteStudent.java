package com.hblolj.bean;

import lombok.Data;

import java.util.List;

/**
 * @author: hblolj
 * @Date: 2019/4/28 14:25
 * @Description:
 * @Version:
 **/
@Data
public class CompleteStudent {

    private Integer studentId;

    private String name;

    private Integer age;

    private String sex;

    private List<CompleteCourse> courses;
}
