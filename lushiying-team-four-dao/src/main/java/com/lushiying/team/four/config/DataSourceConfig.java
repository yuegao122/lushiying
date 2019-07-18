package com.lushiying.team.four.config;

//import com.alibaba.druid.pool.DruidDataSource;
import com.lianjia.common.datasource.DynamicDataSource;
import com.lianjia.common.datasource.aop.TransactionalAspect;
import com.lianjia.mls.common.core.dynamic.DataSourceRouteAspect;
import com.lianjia.mls.common.core.dynamic.MasterSlaveAspect;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.toMap;

@Slf4j
/*@Configuration
@MapperScan("com.lianjia.mls.house.intelligent.tools.domain.dao")*/
public class DataSourceConfig {
    /*private static final String DEFAULT_DATA_SOURCE = "commonMaster";*/

    /**
     * 动态数据源
     *
     * @param dataSources
     *            各个原始数据源
     * @return dataSource
     */
    /*@Bean(name = "dataSource")
    @Primary
    public DynamicDataSource dataSource(List<DruidDataSource> dataSources) {
        log.info("------ 初始化 dynamic 数据源 ------");
        // 将 List 转换为 Map，以 name 属性作为 key
        Map<Object, Object> dataSourceMap = dataSources.stream()
            .collect(toMap(DruidDataSource::getName, Function.identity()));
        DynamicDataSource dataSource = new DynamicDataSource();
        dataSource.setTargetDataSources(dataSourceMap);
        dataSource.setDefaultTargetDataSource(dataSourceMap.get(DEFAULT_DATA_SOURCE));
        return dataSource;
    }

    *//**
     * 数据源路由切面
     *
     * @return dataSourceRouteAspect
     *//*
    @Bean
    public DataSourceRouteAspect dataSourceRouteAspect() {
        return new DataSourceRouteAspect();
    }

    *//**
     * 主从路由切面
     *
     * @return masterSlaveAspect
     *//*
    @Bean
    public MasterSlaveAspect masterSlaveAspect() {
        return new MasterSlaveAspect();
    }

    *//**
     * 事务切面
     *
     * @return transactionalAspect
     *//*
    @Bean
    public TransactionalAspect transactionalAspect() {
        return new TransactionalAspect();
    }*/
}
