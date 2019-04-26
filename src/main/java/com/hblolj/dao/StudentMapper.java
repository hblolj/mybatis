package com.hblolj.dao;

import com.hblolj.bean.Student;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author: hblolj
 * @Date: 2019/4/25 18:12
 * @Description:
 * @Version:
 **/
public interface StudentMapper {

    List<Student> findBySex(String sex);

    @Select("select s.student_id as studentId, s.name, s.age, s.sex from student as s where s.student_id = #{id}")
    Student findById(Integer id);
}
