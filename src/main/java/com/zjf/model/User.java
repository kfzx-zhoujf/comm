package com.zjf.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zjf
 * @create 2020/1/13-11:24
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    private Integer id;
    private String name;
    private String accountId;
    private String token;
    private Long gmtCreate;
    private Long gmtModified;

}
