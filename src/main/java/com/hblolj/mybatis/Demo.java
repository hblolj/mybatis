package com.hblolj.mybatis;

import com.hblolj.bean.Score;
import com.hblolj.bean.ScoreExample;
import com.hblolj.bean.Student;
import com.hblolj.dao.ScoreMapper;
import com.hblolj.dao.StudentMapper;
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
import java.util.List;

/**
 * @author: hblolj
 * @Date: 2019/4/25 16:28
 * @Description:
 * @Version:
 **/
public class Demo {

    public static void main(String[] args) throws FileNotFoundException {

        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory4Xml();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
//            StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
            ScoreMapper mapper = sqlSession.getMapper(ScoreMapper.class);

//            Student student = mapper.findById(1);
            List<Score> scores = mapper.selectByStudentId(1);
//            System.out.println(student.toString());

            for (Score score : scores) {
                System.out.println(score.toString());
            }

//            List<Student> students = mapper.findBySex("male");
//            for (Student student : students) {
//                System.out.println(student.toString());
//            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            sqlSession.close();
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
        configuration.addMapper(StudentMapper.class);
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
