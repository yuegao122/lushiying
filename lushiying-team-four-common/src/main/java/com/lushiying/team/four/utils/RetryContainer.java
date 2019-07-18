package com.lushiying.team.four.utils;

import com.github.rholder.retry.Retryer;
import com.github.rholder.retry.RetryerBuilder;
import com.github.rholder.retry.StopStrategies;
import com.github.rholder.retry.WaitStrategies;
import com.lianjia.hdic.model.pojo.FullHouse;
import com.lianjia.mls.datakeeper.housedel.facade.dto.response.HouseAggregationInfo;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author yuzhibo
 * @date 2019/7/11 10:14
 */
public class RetryContainer {

    public static <T> Retryer<T> getInstance() {
        Retryer<T> retryer = RetryerBuilder
                .<T>newBuilder()
                //如果抛出异常就会进行重试，但是抛出error就不会进行重试的策略
                .retryIfException()
                //进行重试的策略
                .withWaitStrategy(WaitStrategies.fixedWait(10, TimeUnit.SECONDS))
                //进行重试的次数
                .withStopStrategy(StopStrategies.stopAfterAttempt(3))
                .build();
        return retryer;
    }

    public static Retryer<Map<Long,HouseAggregationInfo>> aggregationRetryer = RetryerBuilder
            .<Map<Long,HouseAggregationInfo>>newBuilder()
            //如果抛出异常就重试
            .retryIfException()
            //进行重试的策略
            .withWaitStrategy(WaitStrategies.fixedWait(10, TimeUnit.SECONDS))
            //进行重试的次数
            .withStopStrategy(StopStrategies.stopAfterAttempt(3))
            .build();
}
