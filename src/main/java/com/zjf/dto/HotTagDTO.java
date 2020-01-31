package com.zjf.dto;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

/**
 * @author zjf
 * @create 2020/1/31-14:46
 */
@Data
public class HotTagDTO implements Comparable {
    private String name;
    private Integer priority;

    @Override
    public int compareTo(@NotNull Object o) {
        return this.getPriority() - ((HotTagDTO) o).getPriority();
    }
}
