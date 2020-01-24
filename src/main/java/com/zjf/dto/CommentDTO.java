package com.zjf.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zjf
 * @create 2020/1/24-10:26
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommentDTO {
    private Long parentId;
    private String content;
    private Integer type;
}
