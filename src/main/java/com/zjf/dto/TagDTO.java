package com.zjf.dto;

import lombok.Data;

import java.util.List;

/**
 * @author zjf
 * @create 2020/1/28-10:22
 */
@Data
public class TagDTO {
    private String categoryName;
    private List<String> tags;
}
