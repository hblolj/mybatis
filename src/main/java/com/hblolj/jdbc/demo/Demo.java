package com.hblolj.jdbc.demo;

import com.hblolj.jdbc.bean.Student;
import com.hblolj.jdbc.dao.StudentMapper;
import com.mysql.cj.jdbc.MysqlDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * @author: hblolj
 * @Date: 2019/4/25 16:28
 * @Description:
 * @Version:
 **/
public class Demo {

    public static void main(String[] args) throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory4Xml();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> students = mapper.findBySex("male");
        for (Student student : students) {
            System.out.println(student.toString());
        }
    }

    /**
     * 通过 xml 方式获取 SqlSessionFactory
     * @return
     * @throws FileNotFoundException
     */
    private static SqlSessionFactory getSqlSessionFactory4Xml() throws FileNotFoundException {
        FileInputStream inputStream = new FileInputStream("G:\\mybatis\\src\\main\\resources\\mybatis-config.xml");
        return new SqlSessionFactoryBuilder().build(inputStream);
    }

    /**
     * 通过预定义 Configuration 方式获取 SqlSessionFactory
     * @return
     */
    private static SqlSessionFactory getSqlSessionFactory4Code(){
        DataSource dataSource = getDataSource();
        JdbcTransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("devlopment", transactionFactory, dataSource);
        Configuration configuration = new Configuration(environment);
//        configuration.addMapper();
        return new SqlSessionFactoryBuilder().build(configuration);
    }

    private static DataSource getDataSource(){
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/db_learn?useUnicode=true&characterEncoding=utf-8&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
        dataSource.setUser("root");
        dataSource.setPassword("root");
        return dataSource;
    }
}
