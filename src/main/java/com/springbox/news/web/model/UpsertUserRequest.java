package com.springbox.news.web.model;

import lombok.Data;

/**
 * Запрос на добавление/изменение пользователя
 */
@Data
public class UpsertUserRequest {
    String name;
}
