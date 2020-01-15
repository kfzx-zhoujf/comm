package com.zjf.controller;

import com.zjf.dto.QuestionDTO;
import com.zjf.mapper.UserMapper;
import com.zjf.model.User;
import com.zjf.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author zjf
 * @create 2020/1/9-13:39
 * @controller自动作为spring的bean处理
 */
@Controller
public class IndexController {
    @Autowired
    private UserMapper userMapper;

    //注入questionService获得：获取列表的方法
    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model) {
        //访问首页时，循环看cookies，是否有“token”的数据，有就找到user，并set session
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length != 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    User user = userMapper.findByToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }

        List<QuestionDTO> questionList = questionService.list();
        model.addAttribute("questions", questionList);
        return "index";
    }
}
