package com.lushiying.team.four.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
    /**
     * 路径匹配配置
     */
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer
            // 是否开启后缀模式匹配，如 '/user' 是否匹配 '/user.*'，默认true
            .setUseSuffixPatternMatch(false)
            // 是否开启后缀路径模式匹配，如 '/user' 是否匹配 '/user/'，默认true
            .setUseTrailingSlashMatch(false);
    }

    /**
     * 将对于静态资源的请求转发到 Servlet 容器的默认处理静态资源的 Servlet
     * 因为将 Spring 的拦截模式设置为 "/" 时会对静态资源进行拦截
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    /**
     * 跨原始资源共享配置
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowCredentials(false).allowedOrigins("*").allowedHeaders("*").allowedMethods("GET",
            "POST", "HEAD", "PUT", "DELETE");
    }

    /**
     * HttpMessageConverter
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        // 1. 需要定义一个converter转换消息的对象
        FastJsonHttpMessageConverter fasHttpMessageConverter = new FastJsonHttpMessageConverter();

        // 2. 添加fastjson的配置信息
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.QuoteFieldNames, SerializerFeature.SkipTransientField,
            SerializerFeature.WriteEnumUsingName, SerializerFeature.DisableCircularReferenceDetect,
            SerializerFeature.WriteNonStringKeyAsString);

        // 3. 在converter中添加配置信息
        fasHttpMessageConverter.setFastJsonConfig(fastJsonConfig);

        converters.add(fasHttpMessageConverter);
    }
}
