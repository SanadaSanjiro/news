package com.springbox.news.web.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * Запрос на добавление/изменение пользователя
 */
@Data
public class UpsertUserRequest {
    @NotBlank
    String name;
}
