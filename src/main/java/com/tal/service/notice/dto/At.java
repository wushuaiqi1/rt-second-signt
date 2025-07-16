package com.tal.service.notice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class At {
    private List<String> atMobiles;
    private List<String> atWorkCodes;
    private Boolean isAtAll;
}
