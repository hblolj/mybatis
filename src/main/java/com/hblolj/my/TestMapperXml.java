package com.hblolj.my;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: hblolj
 * @Date: 2019/4/29 8:48
 * @Description:
 * @Version:
 **/
public class TestMapperXml{

    // 我是一个 Xml
    // 有一个 nameSpace
    // 有一个 sql 语句
    public static final String NAME_SPACE = "com.hblolj.my.TestMapper";

    public static final Map<String, String> methodSqlMapping = new HashMap<>();

    static {
        // key 为 sql 名，value 为 sql
        methodSqlMapping.put("selectByStudentId", "select * from student as s where s.student_id = %d");
    }

}
