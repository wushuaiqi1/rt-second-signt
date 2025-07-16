package com.tal.service.notice;


public interface IYachNoticeService {

    /**
     * 发送文本消息至Yach群
     * @param content 消息内容
     * @return 结果
     */
    Boolean sendTextMessage(String content);

    /**
     * 发送MD消息至Yach群
     * @param content 消息内筒
     * @return 发送结果
     */
    Boolean sendMarkDownMessage(String content);
}
