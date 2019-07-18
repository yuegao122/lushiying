package com.lushiying.team.four.config;


import com.alibaba.dubbo.config.*;
import com.lushiying.team.four.config.DubboConfig.DubboProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import java.util.Map;

@Configuration
@ImportResource("classpath:spring/spring-dubbo.xml")
@EnableConfigurationProperties(DubboProperties.class)
public class DubboConfig {
    @ConfigurationProperties(prefix = "spring.dubbo")
    @Getter
    @Setter
    public static class DubboProperties {
        @NestedConfigurationProperty
        private ApplicationConfig application;
        @NestedConfigurationProperty
        private ProtocolConfig protocol;
        private Map<String, RegistryConfig> registries;
        @NestedConfigurationProperty
        private ProviderConfig provider;
        @NestedConfigurationProperty
        private ConsumerConfig consumer;
    }
}
