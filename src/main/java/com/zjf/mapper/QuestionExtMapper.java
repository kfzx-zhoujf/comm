package com.zjf.mapper;

import com.zjf.model.Question;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuestionExtMapper {
    int incView(Question record);
    int incComment(Question record);

}