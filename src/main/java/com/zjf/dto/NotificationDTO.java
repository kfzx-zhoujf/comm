package com.zjf.dto;

import com.zjf.model.User;
import lombok.Data;

/**
 * @author zjf
 * @create 2020/1/29-16:52
 */

@Data
public class NotificationDTO {

    private Long id;
    private Long gmtCreate;
    private Integer status;
    private Long notifier;
    private String notifierName;
    private String outerTitle;
    private Long outerId;
    private String typeName;
    private Integer type;
}
