package com.hblolj.my;

import com.hblolj.bean.Student;

/**
 * @author: hblolj
 * @Date: 2019/4/29 9:20
 * @Description:
 * @Version:
 **/
public class SimpleBootStrap {

    public static void main(String[] args) {
        SimpleSqlSession simpleSqlSession = new SimpleSqlSession();
        TestMapper mapper = simpleSqlSession.getMapper(TestMapper.class);
        Student student = mapper.selectByStudentId(1);
        System.out.println(student.toString());
    }
}
