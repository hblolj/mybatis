package com.hblolj.dao;

import com.hblolj.bean.CompleteStudent;
import com.hblolj.bean.Student;
import org.apache.ibatis.annotations.Param;
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

    List<Student> findBySexLimit(@Param("sex") String sex, @Param("index") Integer index, @Param("num") Integer num);

    @Select("select s.student_id as studentId, s.name, s.age, s.sex from student as s where s.student_id = #{id}")
    Student findById(Integer id);

    Integer insertStudent(@Param("student") Student student);

    CompleteStudent findByStudentId(Integer studentId);

    CompleteStudent findStudentByNestSelect(Integer studentId);

    Integer batchInsertStudent(@Param("students") List<Student> students);

    Integer batchUpdateStudent1(@Param("name") String name, @Param("ids") List<Integer> ids);

    Integer batchUpdateStudent2(@Param("students") List<Student> students);
}
