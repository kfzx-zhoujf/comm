package com.zjf.dto;

import lombok.Data;

/**
 * @author zjf
 * @create 2020/1/31-8:05
 */
@Data
public class QuestionQueryDTO {
    private String search;
    private Integer page;
    private Integer size;
}
