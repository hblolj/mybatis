package com.hblolj.jdbc;

import com.hblolj.jdbc.bean.Student;

import java.sql.*;

/**
 * @author: hblolj
 * @Date: 2019/4/22 18:11
 * @Description:
 * @Version:
 **/
public class JDBCDemo {

    public static void main(String[] args) {

//        insert();
        get(8);
    }

    private static void insert() {
        Connection connection = null;

        try {
            //1. 加载驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //2. 获取连接
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_learn?useUnicode=true&characterEncoding=utf-8&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "root");
//            connection.setAutoCommit(false);
            //3. 创建 Statement
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO student(student_id, name, age, sex) VALUES (?,?,?,?)");
            preparedStatement.setInt(1, 8);
            preparedStatement.setString(2, "jdbc");
            preparedStatement.setInt(3, 24);
            preparedStatement.setString(4, "male");
//            connection.commit();
            //4. 执行 Statement
            int result = preparedStatement.executeUpdate();
            //5. 处理 ResultSet
            System.out.println(result);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            //6. 关闭连接，释放资源
            try {
                if (null != connection){
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void get(int id){
        Connection connection = null;

        try {
            //1. 加载驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //2. 获取连接
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_learn?useUnicode=true&characterEncoding=utf-8&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "root");
//            connection.setAutoCommit(false);
            //3. 创建 Statement
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT student_id, name, age, sex FROM student WHERE student_id = ?");
            preparedStatement.setInt(1, id);
//            connection.commit();
            //4. 执行 Statement
            ResultSet rs = preparedStatement.executeQuery();
            //5. 处理 ResultSet
            while (rs.next()){
                Student student = new Student();
                student.setStudentId(rs.getInt(1));
                student.setName(rs.getString(2));
                student.setAge(rs.getInt(3));
                student.setSex(rs.getString(4));
                System.out.println(student);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            //6. 关闭连接，释放资源
            try {
                if (null != connection){
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
