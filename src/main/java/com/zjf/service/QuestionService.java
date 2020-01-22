package com.zjf.service;

import com.zjf.dto.PaginationDTO;
import com.zjf.dto.QuestionDTO;
import com.zjf.exception.CustomizeErrorCode;
import com.zjf.exception.CustomizeException;
import com.zjf.mapper.QuestionMapper;
import com.zjf.mapper.UserMapper;
import com.zjf.model.Question;
import com.zjf.model.QuestionExample;
import com.zjf.model.User;
import org.apache.ibatis.session.RowBounds;
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

    public PaginationDTO list(Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();
        //通过example创建
        Integer totalCount = (int) questionMapper.countByExample(new QuestionExample());
        //Integer totalCount =  questionMapper.count();
        paginationDTO.setPagination(totalCount, page, size);


        Integer offset = (page - 1) * size;
        //List<Question> questions = questionMapper.list(offset, size);
        //用生成器的插件实现分页
        List<Question> questions = questionMapper.selectByExampleWithRowbounds(new QuestionExample(), new RowBounds(offset, size));

        List<QuestionDTO> questionDTOList = new ArrayList<>();

        for (Question question : questions) {
            //User user = userMapper.findById(question.getCreator());
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            //spring内置方法beanUtils,copy去设置新建DTO中的参数
            BeanUtils.copyProperties(question, questionDTO);
            //再加上dto新增的user对象
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);

        return paginationDTO;
    }

    public PaginationDTO list(Integer userId, Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();
        //Integer totalCount = questionMapper.countByUserId(userId);
        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria().andCreatorEqualTo(userId);
        Integer totalCount = (int) questionMapper.countByExample(questionExample);
        paginationDTO.setPagination(totalCount, page, size);


        Integer offset = (page - 1) * size;
        //List<Question> questions = questionMapper.listByUserId(userId, offset, size);
        QuestionExample example = new QuestionExample();
        example.createCriteria().andCreatorEqualTo(userId);
        List<Question> questions = questionMapper.selectByExampleWithRowbounds(example, new RowBounds(offset, size));

        List<QuestionDTO> questionDTOList = new ArrayList<>();

        for (Question question : questions) {
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            //spring内置方法beanUtils,copy去设置新建DTO中的参数
            BeanUtils.copyProperties(question, questionDTO);
            //再加上dto新增的user对象
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);

        return paginationDTO;
    }

    public QuestionDTO getById(Integer id) {
        //新建一个question类从mapper获取
        //Question question = questionMapper.getById(id);
        Question question = questionMapper.selectByPrimaryKey(id);

        //如果id为空，则出错
        if (question == null) {
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }


        //新建一个dto，将question传到dto
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question, questionDTO);
        //新建一个user，将user传到dto
        User user = userMapper.selectByPrimaryKey(question.getCreator());
        questionDTO.setUser(user);
        return questionDTO;
    }

    public void createOrUpdate(Question question) {
        if (question.getId() == null) {
            //创建
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            //questionMapper.create(question);
            questionMapper.insert(question);
        } else {
            //更新
            //question.setGmtModified(System.currentTimeMillis());
            //questionMapper.update(question);
            Question updateQuestion = new Question();
            updateQuestion.setGmtModified(System.currentTimeMillis());
            updateQuestion.setTitle(question.getTitle());
            updateQuestion.setDescription(question.getDescription());
            updateQuestion.setTag(question.getTag());
            QuestionExample example = new QuestionExample();
            example.createCriteria().andIdEqualTo(question.getId());
            int updated = questionMapper.updateByExampleSelective(updateQuestion, example);
            if (updated != 1) {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
        }

    }
}
