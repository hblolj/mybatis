package com.hblolj.mybatis;

import com.hblolj.bean.*;
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
import java.util.ArrayList;
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
//            findBySex(sqlSession);
//            findCourseById(sqlSession, 1);
//            findCompleteScoreByStudentIdAndCourseId(sqlSession, 1, 3);
//            findCompleteScoreByStudentId(sqlSession, 2);
//            findCompleteStudentByStudentId(sqlSession, 1);
//            findCompleteStudentByNestSelect(sqlSession, 2);
//            findStudentByNestSelect(sqlSession, 1);
//            batchAddStudent(sqlSession, 12, 19);
//            batchUpdateStudent1(sqlSession, 12, 19);
            batchUpdateStudent2(sqlSession);
//            findBySexLimit(sqlSession, "none", 10, 10);
            sqlSession.commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            sqlSession.close();
        }
    }

    // 分页
    private static void findBySexLimit(SqlSession sqlSession, String sex, Integer index, Integer num){
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> students = mapper.findBySexLimit(sex, index, num);
        students.forEach(System.out::println);
    }

    // 批量插入
    private static void batchAddStudent(SqlSession sqlSession, Integer startId, Integer num){
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> students = new ArrayList<>();
        while (num > 0){
            students.add(new Student(startId, "robot" + startId, 0, "none"));
            startId++;
            num--;
        }
        if (students.size() > 0){
            Integer result = mapper.batchInsertStudent(students);
            System.out.println(result);
        }
    }

    // 批量更新
    // 1. 更新多条记录，指定字段为相同的值
    private static void batchUpdateStudent1(SqlSession sqlSession, Integer startId, Integer num){
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        List<Integer> ids = new ArrayList<>();
        while (num > 0){
            ids.add(startId++);
            num--;
        }
        Integer result = mapper.batchUpdateStudent1("robot", ids);
        System.out.println(result);
    }

    // 2. 更新多条记录，指定字段为不同的值
    private static void batchUpdateStudent2(SqlSession sqlSession){
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> students = mapper.findBySex("none");
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            student.setName(student.getName() + "-c");
            student.setAge(i);
        }
        Integer result = mapper.batchUpdateStudent2(students);
        System.out.println(result);
    }

    // 嵌套查询，一对多，一个 Student 多个 Course
    private static void findStudentByNestSelect(SqlSession sqlSession, Integer studentId){
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        CompleteStudent completeStudent = mapper.findStudentByNestSelect(studentId);
        System.out.println(completeStudent.toString());
    }

    // 嵌套查询 一对一，一个 Socre 对应一个 Student 与一个 Course
    private static void findCompleteStudentByNestSelect(SqlSession sqlSession, Integer studentId){
        ScoreMapper mapper = sqlSession.getMapper(ScoreMapper.class);
        // 嵌套查询
        List<CompleteScore> completeScores = mapper.selectCompleteByNestSelect(studentId);
        for (CompleteScore completeScore : completeScores) {
            System.out.println(completeScore.toString());
        }
    }

    // 嵌套结果，一对多
    private static void findCompleteStudentByStudentId(SqlSession sqlSession, Integer studentId){
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        CompleteStudent completeStudent = mapper.findByStudentId(studentId);
        System.out.println(completeStudent.toString());
    }

    // 嵌套结果 一对一
    private static void findCompleteScoreByStudentId(SqlSession sqlSession, Integer studentId){
        ScoreMapper mapper = sqlSession.getMapper(ScoreMapper.class);
        List<CompleteScore> completeScores = mapper.selectCompleteByStudentId(studentId);
        for (CompleteScore completeScore : completeScores) {
            System.out.println(completeScore.toString());
        }
    }

    // 嵌套结果 一对一
    private static void findCompleteScoreByStudentIdAndCourseId(SqlSession sqlSession, Integer studentId, Integer courseId){
        ScoreMapper mapper = sqlSession.getMapper(ScoreMapper.class);
        CompleteScore score = mapper.selectCompleteByStudentIdAndCourseId(studentId, courseId);
        System.out.println(score.toString());
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
