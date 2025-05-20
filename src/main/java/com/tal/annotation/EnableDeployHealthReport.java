package com.tal.annotation;


import com.tal.config.DeployHealthReportAutoConfiguration;
import com.tal.service.handler.DeployHealthReporter;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

// 插拔式开启部署结果上报
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({DeployHealthReporter.class, DeployHealthReportAutoConfiguration.class})
public @interface EnableDeployHealthReport {

}
