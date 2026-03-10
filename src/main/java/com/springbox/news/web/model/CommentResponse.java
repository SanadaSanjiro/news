package com.springbox.news.web.model;

import lombok.Data;

import java.time.Instant;

@Data
public class CommentResponse {
    long id;
    NewsShortResponse news;
    UserResponse comment_author;
    String text;
    Instant createdAt;
    Instant updatedAt;
}
