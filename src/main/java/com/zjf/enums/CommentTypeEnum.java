package com.zjf.enums;

/**
 * @author zjf
 * @create 2020/1/24-15:07
 */

public enum CommentTypeEnum {
    QUESTION(1),
    COMMENT(2);
    private int type;

    public static boolean isExist(Integer type) {
        for (CommentTypeEnum commentTypeEnum : CommentTypeEnum.values()) {
            if(commentTypeEnum.getType()==type){
                return true;
            }
        }
        return false;
    }

    public int getType() {
        return type;
    }

    CommentTypeEnum(int type) {
        this.type = type;
    }
}
