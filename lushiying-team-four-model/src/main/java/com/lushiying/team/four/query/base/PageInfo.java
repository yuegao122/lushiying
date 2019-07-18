package com.lushiying.team.four.query.base;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author yuzhibo
 * @date 2019/6/3 12:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageInfo<T> {

    private Integer currentPage;

    private Integer pageSize;

    private Long total;

    private Long totalPage;

    private List<T> list;

    long getTotalPage(){
        long pages = total / pageSize;
        //非整数页
        if (total % pageSize != 0){
            pages++;
        }
        return pages;
    }
}
