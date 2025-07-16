package com.tal.config;

import com.tal.annotation.EnableDeployHealthReport;
import com.tal.service.notice.IYachNoticeService;
import com.tal.service.notice.YachNoticeServiceImpl;
import okhttp3.OkHttpClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnBean(annotation = EnableDeployHealthReport.class)
// 类上条件注解：保护整个配置类，推荐。
// 方法上条件注解：只保护单个Bean，容易因依赖关系报错，不推荐用于全局依赖Bean。
public class BeanConfig {
    @Bean
    // @ConditionalOnBean(annotation = EnableDeployHealthReport.class)
    public IYachNoticeService yachNoticeService() {
        return new YachNoticeServiceImpl();
    }

    @Bean
    // @ConditionalOnBean(annotation = EnableDeployHealthReport.class)
    public OkHttpClient okHttpClient() {
        return new OkHttpClient().newBuilder().build();
    }
}
