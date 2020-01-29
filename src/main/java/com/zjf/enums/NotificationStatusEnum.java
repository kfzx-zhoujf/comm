package com.zjf.enums;

/**
 * @author zjf
 * @create 2020/1/29-16:07
 */

public enum NotificationStatusEnum {
    UNREAD(0),
    READ(1)
    ;
    private int status;

    public int getStatus() {
        return status;
    }

    NotificationStatusEnum(int status) {
        this.status = status;
    }
}
