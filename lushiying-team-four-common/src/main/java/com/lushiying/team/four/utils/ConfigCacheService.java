package com.lushiying.team.four.utils;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author yuzhibo
 * @date 2019/6/24 18:37
 */
@Service
@Slf4j
public class ConfigCacheService {

    private final LoadingCache<Long, String> CONFIG_CACHE
            //该构造函数为私有，只能通过静态方法newBuilder()来获得CacheBuilder的实例
            = CacheBuilder.newBuilder()
            //设置并发级别为8，并发级别是指可以同时写缓存的线程数
            .concurrencyLevel(8)
            //设置写缓存后1天过期
            .expireAfterWrite(1, TimeUnit.DAYS)
            //设置缓存的初始容量为10
            .initialCapacity(10)
            //设置缓存的最大容量为10240，超过10240之后会按照LRU最近最少使用算法来移除缓存项
            .maximumSize(10240)
            //设置缓存移除通知
            .removalListener(notification -> log.info(notification.getKey() + "was renoved,cause is" + notification.getCause()))
            .build(new CacheLoader<Long, String>() {
                @Override
                public String load(Long key) throws Exception {
                    return StringUtils.EMPTY;
                }
            });

    public String getValue(Long housedelCodeB) throws Exception{
        String houseCodeC = CONFIG_CACHE.get(housedelCodeB);
        return houseCodeC;
    }

    public void putVlue(Long housedelCodeB,String housedelCodeC){
        CONFIG_CACHE.put(housedelCodeB,housedelCodeC);
    }
}
