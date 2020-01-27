package com.zjf.mapper;

import com.zjf.model.Comment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentExtMapper {
    int incComment(Comment record);
}