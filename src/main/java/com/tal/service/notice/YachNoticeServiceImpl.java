package com.tal.service.notice;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.tal.config.DeployHealthReportAutoConfiguration;
import com.tal.consts.MsgTypeConst;
import com.tal.service.notice.dto.MarkDown;
import com.tal.service.notice.dto.MarkDownMessageRequest;
import com.tal.service.notice.dto.Text;
import com.tal.service.notice.dto.TextMessageRequest;
import com.tal.utils.TimeUtils;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

// @Service 仅用于 Spring 环境，SPI 不依赖此注解
@Slf4j
public class YachNoticeServiceImpl implements IYachNoticeService {
    @Resource
    private OkHttpClient okHttpClient;
    @Resource
    private DeployHealthReportAutoConfiguration deployHealthReportAutoConfiguration;

    public static final MediaType json =  MediaType.get("application/json; charset=utf-8");

    @Override
    public Boolean sendTextMessage(String content) {
        // template build
        HashMap<String, String> template = new HashMap<>();
        template.put("项目", deployHealthReportAutoConfiguration.getAppName());
        template.put("环境", deployHealthReportAutoConfiguration.getAppEnv());
        template.put("时间", TimeUtils.getCurrentFormatTime());
        template.put("部署状态", content);
        content = JSON.toJSONString(template, SerializerFeature.WriteMapNullValue);

        // datasource build
        Text text = Text.builder()
                .content(content)
                .build();
        TextMessageRequest messageRequest = TextMessageRequest.builder()
                .text(text)
                .msgtype(MsgTypeConst.TEXT)
                .build();
        String jsonMessageRequest = JSON.toJSONString(messageRequest);
        return this.sendMessage(jsonMessageRequest);
    }

    @Override
    public Boolean sendMarkDownMessage(String content) {
        // template build
        String mdText =  String.format("【项目】%s\n【环境】%s\n【时间】%s\n【部署状态】%s\n",
                deployHealthReportAutoConfiguration.getAppName(),
                deployHealthReportAutoConfiguration.getAppEnv(),
                TimeUtils.getCurrentFormatTime(),
                content
        );

        // datasource build
        MarkDown md = MarkDown.builder()
                .title(deployHealthReportAutoConfiguration.getAppName())
                .text(mdText)
                .build();
        MarkDownMessageRequest markDownMessageRequest = MarkDownMessageRequest.builder()
                .markdown(md)
                .build();
        String jsonMessageRequest = JSON.toJSONString(markDownMessageRequest,SerializerFeature.WriteMapNullValue);

        // send message
        return this.sendMessage(jsonMessageRequest);
    }

    private Boolean sendMessage(String body){
        // request build
        RequestBody requestBody = RequestBody.create(body, json);
        Request req = new Request.Builder()
                .url(deployHealthReportAutoConfiguration.getEndpoint())
                .post(requestBody)
                .build();

        // call
        Response resp = null;
        try {
            resp = okHttpClient.newCall(req).execute();
            if (!resp.isSuccessful()) {
                log.info("sendMessage fail:{}", resp.message());
                return Boolean.FALSE;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return Boolean.TRUE;
    }
}
