package com.zjf.controller;

import com.zjf.dto.CommentDTO;
import com.zjf.dto.ResultDTO;
import com.zjf.exception.CustomizeErrorCode;
import com.zjf.model.Comment;
import com.zjf.model.User;
import com.zjf.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zjf
 * @create 2020/1/24-10:16
 */
@Controller
public class CommentController {


    @Autowired
    private CommentService commentService;

    //使用json的方式接收
    @ResponseBody
    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public Object post(@RequestBody CommentDTO commentDTO,
                       HttpServletRequest request) {
        //通过request拿到user
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }
        Comment comment = new Comment();
        comment.setParentId(commentDTO.getParentId());
        comment.setContent(commentDTO.getContent());
        comment.setType(commentDTO.getType());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setCommentator(user.getId());
        comment.setLikeCount(0L);
        commentService.insert(comment);
        //定义一个对象，ResponseBody自动序列化成一个json返回前端
        return ResultDTO.okOf();
    }
}
