package com.springbox.news.web.model;

import lombok.Data;

/**
 * Запрос на добавление/изменение комментария
 */
@Data
public class UpsertCommentRequest {
    long newsId;
    long userId;
    String text;
}
