package com.tal.service.notice.dto;

import com.tal.consts.MsgTypeConst;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class MarkDownMessageRequest {
    private final String msgtype = MsgTypeConst.MARKDOWN;
    private At at;
    private MarkDown markdown;
}
