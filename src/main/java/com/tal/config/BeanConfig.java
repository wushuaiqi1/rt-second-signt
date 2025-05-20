package com.tal.config;

import com.tal.service.notice.IYachNoticeService;
import com.tal.service.notice.YachNoticeServiceImpl;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {
    @Bean
    public IYachNoticeService yachNoticeService() {
        return new YachNoticeServiceImpl();
    }

    @Bean
    public OkHttpClient okHttpClient() {
        return new OkHttpClient()
                .newBuilder()
                .build();
    }
}
