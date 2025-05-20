package com.tal.service.notice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class MarkDown {
    private String title;
    private String image;
    private String text;
}
