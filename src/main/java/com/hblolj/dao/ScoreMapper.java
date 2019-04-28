package com.hblolj.dao;

import com.hblolj.bean.CompleteScore;
import com.hblolj.bean.Score;
import com.hblolj.bean.ScoreExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ScoreMapper {

    int countByExample(ScoreExample example);

    int deleteByExample(ScoreExample example);

    int insert(Score record);

    int insertSelective(Score record);

    List<Score> selectByExample(ScoreExample example);

    List<Score> selectByStudentId(Integer studentId);

    CompleteScore selectCompleteByStudentIdAndCourseId(@Param("studentId") Integer studentId, @Param("courseId") Integer courseId);

    List<CompleteScore> selectCompleteByStudentId(@Param("studentId") Integer studentId);

    List<CompleteScore> selectCompleteByNestSelect(Integer studentId);

    int updateByExampleSelective(@Param("record") Score record, @Param("example") ScoreExample example);

    int updateByExample(@Param("record") Score record, @Param("example") ScoreExample example);
}