package com.tal.service.handler;

import com.tal.service.notice.IYachNoticeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.health.HealthComponent;
import org.springframework.boot.actuate.health.HealthEndpoint;
import org.springframework.boot.actuate.health.Status;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import javax.annotation.Resource;
import java.util.HashMap;

@Slf4j
public class DeployHealthReporter {
    @Resource
    private HealthEndpoint healthEndpoint;
    @Resource
    private IYachNoticeService yachNoticeService;

    @EventListener(ApplicationReadyEvent.class)
    public void reportAfterDeploy() {
        log.info("reportAfterDeploy start");
        HealthComponent health = healthEndpoint.health();
        boolean res = yachNoticeService.sendMarkDownMessage(health.getStatus().toString());
        log.info("reportAfterDeploy end 消息发送结果:{}", res);
    }

}
