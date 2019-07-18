package com.lushiying.team.four.query.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yuzhibo
 * @date 2019/6/3 11:36
 * 分页工具类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Page {

    public static final Integer DEFAULT_LIMIT = 10;

    public static final Integer DEFAULT_OFFSET = 0;

    private Integer offset;

    private Integer limit;

    /**
     * 获取page分页信息
     * @param offset
     * @param limit
     * @return
     */
    public static Page getPage(int offset,int limit){
        Page page = new Page();
        if (offset <= 0){
            offset = 0;
        }
        if (limit <= 0){
            limit = DEFAULT_LIMIT;
        }
        page.setLimit(limit);
        page.setOffset(offset);
        return page;
    }

    /**
     * 根据pageNo与pageSize获取分页信息
     * @param pageNo
     * @param pageSize
     * @return
     */
    public static Page getPageByPageNo(int pageNo,int pageSize){
        if (pageNo <= 0){
            pageNo = 1;
        }
        if (pageSize <= 0){
            pageSize = DEFAULT_LIMIT;
        }
        Page page = new Page();
        page.setOffset((pageNo - 1) * pageSize);
        page.setLimit(pageSize);
        return page;
    }
}
