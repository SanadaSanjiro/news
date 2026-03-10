package com.springbox.news.web.model;

import lombok.Data;

import java.time.Instant;

@Data
public class NewsShortResponse {
    long id;
    UserResponse author;
    NewsCategoryResponse category;
    int commentCount;
    String title;
    String text;
    Instant createdAt;
    Instant updatedAt;
}
