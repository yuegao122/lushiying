package com.lushiying.team.four.dao.base;


import java.util.List;

/**
 * @author yuzhibo
 * @date 2019/6/2 14:00
 */
public interface BaseMapper<M, Q> {

    /**
     * 添加数据
     *
     * @param model
     * @return
     */
    long insert(M model);

    /**
     * 通过id获取数据
     *
     * @param id
     * @return
     */
    M getById(long id);

    /**
     * 更新数据
     *
     * @param model
     * @return
     */
    int update(M model);

    /**
     * 获取数量
     *
     * @param queryModel
     * @return
     */
    int count(Q queryModel);

    /**
     * 获取列表数据信息
     *
     * @param queryModel
     * @return
     */
    List<M> findList(Q queryModel);
}
