package com.zjf.mapper;

import com.zjf.model.Question;
import com.zjf.model.QuestionExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

@Mapper
public interface QuestionExtMapper {
    int incView(Question record);
    int incComment(Question record);

}