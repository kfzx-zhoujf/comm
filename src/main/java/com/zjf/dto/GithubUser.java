package com.zjf.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zjf
 * @create 2020/1/10-16:32
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class GithubUser {
    private String name;
    private Long id;
    private String bio;
}
