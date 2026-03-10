package com.springbox.news.web.model;

import lombok.Data;

@Data
public class UpsertCommentRequest {
    long newsId;
    long userId;
    String text;
}
