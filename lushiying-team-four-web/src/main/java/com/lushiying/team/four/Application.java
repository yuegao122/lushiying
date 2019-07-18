package com.lushiying.team.four;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication(scanBasePackages = "com.lushiying.team.four",
        exclude = DataSourceAutoConfiguration.class)
@Slf4j
@EnableAsync
@ServletComponentScan(basePackages = "com.lianjia.mls.common.util.startcheck")
public class Application implements CommandLineRunner {
    public static void main(String[] args) {
        // dubbo使用日志顺序 log4j->slf4j->common logging->jdk log
        System.setProperty("dubbo.application.logger", "slf4j");
        // 设置 druid 使用 slf4j 记录日志
        System.setProperty("druid.logType", "slf4j");
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) {
        log.info("mls-data-group-test application is running...");
    }
}
