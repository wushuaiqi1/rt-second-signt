package com.tal.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import java.util.List;

@Data
@ConfigurationProperties(prefix = "health.report") // 宽松绑定变量
public class DeployHealthReportAutoConfiguration {
    // 上报端点
    private String endpoint;
    // 服务名称
    private String appName;
    // 当前环境
    private String appEnv;

}
