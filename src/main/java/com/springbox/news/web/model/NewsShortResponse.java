package com.springbox.news.web.model;

import lombok.Data;

import java.time.Instant;

/**
 * DTO для предоставления информации в списках новостей
 */
@Data
public class NewsShortResponse {
    long id;
    UserResponse author;
    NewsCategoryResponse category;
    int commentCount;                   // содержит количество комментариев к данной новости
    String title;
    String text;
    Instant createdAt;
    Instant updatedAt;
}
