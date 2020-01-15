package com.zjf.service;

import com.zjf.dto.QuestionDTO;
import com.zjf.mapper.QuestionMapper;
import com.zjf.mapper.UserMapper;
import com.zjf.model.Question;
import com.zjf.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zjf
 * @create 2020/1/15-10:04
 * 组装，可以同时用questionMapper+userMapper
 */
@Service
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    public List<QuestionDTO> list() {
        List<Question> questions = questionMapper.list();
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question : questions) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            //spring内置方法beanUtils,copy去设置新建DTO中的参数
            BeanUtils.copyProperties(question, questionDTO);
            //再加上dto新增的user对象
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        return questionDTOList;
    }
}
