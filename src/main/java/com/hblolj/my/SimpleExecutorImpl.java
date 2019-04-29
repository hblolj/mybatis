package com.hblolj.my;

import com.hblolj.bean.Student;

import java.sql.*;

/**
 * @author: hblolj
 * @Date: 2019/4/29 9:04
 * @Description:
 * @Version:
 **/
public class SimpleExecutorImpl implements SimpleExecutor{

    @Override
    public <E> E query(String statement, Object parameter) {

        // JDBC
        String url = "jdbc:mysql://localhost:3306/db_learn?useUnicode=true&characterEncoding=utf-8&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String username = "root";
        String password = "root";
        try {
            // loadDriver -> config
            Class.forName("com.mysql.jdbc.Driver");
            // getConnection -> config
            Connection connection = DriverManager.getConnection(url, username, password);
            // create Statement -> statement + parameter 这里写死了
            PreparedStatement ps = connection.prepareStatement(String.format(statement, Integer.parseInt(String.valueOf(parameter))));
            // executor statement ->
            ResultSet rs = ps.executeQuery();
            // handler result -> 按 resultMap or resultType 进行适配
            Student student = new Student();
            while (rs.next()){
                student.setStudentId(rs.getInt("student_id"));
                student.setName(rs.getString("name"));
                student.setAge(rs.getInt("age"));
                student.setSex(rs.getString("sex"));
            }
            return (E) student;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
