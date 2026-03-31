package com.springbox.news.web.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * Запрос на добавление/изменение комментария
 */
@Data
public class UpsertCommentRequest {
    @Min(0)
    long newsId;
    @Min(0)
    long userId;
    @NotBlank
    String text;
}
