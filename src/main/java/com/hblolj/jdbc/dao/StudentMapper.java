package com.hblolj.jdbc.dao;

import com.hblolj.jdbc.bean.Student;

import java.util.List;

/**
 * @author: hblolj
 * @Date: 2019/4/25 18:12
 * @Description:
 * @Version:
 **/
public interface StudentMapper {

    List<Student> findBySex(String sex);
}
