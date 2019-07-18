package com.lushiying.team.four.query.base;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yuzhibo
 * @date 2019/6/3 12:50
 */
public class OrderByOperation {

    /**
     * 操作符集合
     */
    private List<OrderByItem> itemOperators = new ArrayList<>();

    public static final String ORDER_OPERATOR_ASC = "asc";

    public static final String ORDER_OPERATOR_DESC = "desc";

    public List<OrderByItem> getItemOperators() {
        return itemOperators;
    }

    public void setItemOperators(List<OrderByItem> itemOperators) {
        this.itemOperators = itemOperators;
    }

    /**
     * 添加操作单项
     * @param item
     */
    public void addOrderItem(OrderByItem item){
        itemOperators.add(item);
    }

    /**
     * 添加操作单项
     * @param column
     * @param operator
     */
    public void addOrderItem(String column, String operator){
        itemOperators.add(new OrderByItem(column, operator));
    }

    private static class OrderByItem {
        /**
         * 字段名称
         */
        private String column;
        /**
         * 操作 asc或者desc
         */
        private String operator;

        public OrderByItem(){}

        private OrderByItem(String column,String operator){
            this.column = column;
            this.operator = operator;
        }

        public String getColumn(){
            return column;
        }

        public void setColumn(String column){
            this.column = column;
        }

        public String getOperator(){
            return operator;
        }

        public void setOperator(String operator){
            this.operator = operator;
        }
    }
}
