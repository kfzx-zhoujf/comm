package com.zjf.cache;

import com.zjf.dto.HotTagDTO;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.lang.model.element.VariableElement;
import java.util.*;

/**
 * @author zjf
 * @create 2020/1/31-14:01
 */

@Component
@Data
public class HotTagCache {
    //private Map<String, Integer> tags = new HashMap<>();
    private List<String> hots = new ArrayList<>();

    /*private final static Map<String,Integer> tags=new HashMap<>();
    public static synchronized Map<String,Integer> getTags(){
        return tags;
    }*/
    public void updateTags(Map<String, Integer> tags) {
        int max = 3;
        //java的优先队列
        PriorityQueue<HotTagDTO> priorityQueue = new PriorityQueue<>(max);
        tags.forEach((name, priority) -> {
            HotTagDTO hotTagDTO = new HotTagDTO();
            hotTagDTO.setName(name);
            hotTagDTO.setPriority(priority);
            //取3个最大值，如果队列小于3，添加
            if (priorityQueue.size() < max) {
                priorityQueue.add(hotTagDTO);
            } else {
                //大于时，比较队列最小的于当前值，大于则替换
                HotTagDTO minHot = priorityQueue.peek();
                if (hotTagDTO.compareTo(minHot) > 0) {
                    priorityQueue.poll();
                    priorityQueue.add(hotTagDTO);
                }
            }
        });
        List<String> sortedTags = new ArrayList<>();
        HotTagDTO poll = priorityQueue.poll();
        while (poll != null) {
            sortedTags.add(0, poll.getName());
            poll = priorityQueue.poll();
        }
        hots = sortedTags;
        System.out.println(hots);
    }
}
