package com.lushiying.team.four.query.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yuzhibo
 * @date 2019/6/3 11:34
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseQM {

    /**
     * 主键id
     */
    private Long id;

    /**
     * 是否为降序
     */
    private Boolean isDesc;

    /**
     * 分页
     */
    private Page page;

    /**
     * 状态
     */
    private Integer status;

    /**
     * order by 相关操作，支持多个字段
     */
    private OrderByOperation orderByOperation;

    public static final String COL_ID = "id";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_CREATE_TIME = "create_time";

    /**
     * 添加排序
     * @param orderColumn
     */
    public void addOrderAsc(String orderColumn){
        if(orderByOperation == null){
            orderByOperation = new OrderByOperation();
        }

        orderByOperation.addOrderItem(orderColumn, OrderByOperation.ORDER_OPERATOR_ASC);
    }

    public void addOrderDesc(String orderColumn){
        if(orderByOperation == null){
            orderByOperation = new OrderByOperation();
        }

        orderByOperation.addOrderItem(orderColumn, OrderByOperation.ORDER_OPERATOR_DESC);
    }
}
