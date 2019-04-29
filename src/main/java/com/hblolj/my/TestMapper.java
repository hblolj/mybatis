package com.hblolj.my;

import com.hblolj.bean.Student;

/**
 * @author: hblolj
 * @Date: 2019/4/29 8:47
 * @Description:
 * @Version:
 **/
public interface TestMapper {

    // SqlSession -> Executor -> query ->
    Student selectByStudentId(Integer studentId);
}
