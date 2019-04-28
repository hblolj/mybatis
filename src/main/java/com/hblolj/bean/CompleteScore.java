package com.hblolj.bean;

import lombok.Data;

/**
 * @author: hblolj
 * @Date: 2019/4/28 13:47
 * @Description:
 * @Version:
 **/
@Data
public class CompleteScore {

    private Student student;

    private Course course;

    private Integer score;
}
