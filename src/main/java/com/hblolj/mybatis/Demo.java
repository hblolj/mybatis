package com.hblolj.mybatis;

import com.hblolj.bean.Course;
import com.hblolj.bean.Score;
import com.hblolj.bean.Student;
import com.hblolj.dao.CourseMapper;
import com.hblolj.dao.ScoreMapper;
import com.hblolj.dao.StudentMapper;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.apache.ibatis.type.TypeHandler;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.List;

/**
 * @author: hblolj
 * @Date: 2019/4/25 16:28
 * @Description:
 * @Version:
 **/
public class Demo {

    public static void main1(String[] args) {
        System.out.println(Demo.class.getResource(""));
        System.out.println(Demo.class.getResource("/"));
        System.out.println();
        System.out.println(Demo.class.getClassLoader().getResource(""));
        System.out.println(Demo.class.getClassLoader().getResource("/"));
        System.out.println(Demo.class.getClassLoader().getResource("mapper/StudentMapper.xml"));
        System.out.println(Demo.class.getClassLoader().getResource("/mapper/StudentMapper.xml"));
    }

    public static void main(String[] args) throws IOException {

        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory4Xml();
        // 无参的 openSession 默认 autoCommit 为 false，所以在非 query 时，需要手动 commit
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
//            addNewStudent(sqlSession, 11);
//            findStudentScore(sqlSession);
//            findById(sqlSession);
            findBySex(sqlSession);
//            findCourseById(sqlSession, 1);
            sqlSession.commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            sqlSession.close();
        }
    }

    private static void findCourseById(SqlSession sqlSession, Integer courseId){
        CourseMapper mapper = sqlSession.getMapper(CourseMapper.class);
        Course course = mapper.findByCourseId(courseId);
        System.out.println(course);
    }

    private static void findStudentScore(SqlSession sqlSession){
        ScoreMapper mapper = sqlSession.getMapper(ScoreMapper.class);
        List<Score> scores = mapper.selectByStudentId(1);
        for (Score score : scores) {
            System.out.println(score.toString());
        }
    }

    private static void findById(SqlSession sqlSession){
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        Student student = mapper.findById(1);
        System.out.println(student.toString());
    }

    private static void findBySex(SqlSession sqlSession){
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> students = mapper.findBySex("male");
        for (Student student : students) {
            System.out.println(student.toString());
        }
    }

    private static void addNewStudent(SqlSession sqlSession, Integer studentId){
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        Student student = new Student();
        student.setStudentId(studentId);
        student.setName("typeHandler2");
        student.setAge(18);
        student.setSex("male");
        Integer result = mapper.insertStudent(student);
        System.out.println(result);
    }

    /**
     * 通过 xml 方式获取 SqlSessionFactory
     * @return
     * @throws FileNotFoundException
     */
    private static SqlSessionFactory getSqlSessionFactory4Xml() throws IOException {
//        FileInputStream inputStream = new FileInputStream("G:\\mybatis\\src\\main\\resources\\mybatis-config.xml");
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
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
