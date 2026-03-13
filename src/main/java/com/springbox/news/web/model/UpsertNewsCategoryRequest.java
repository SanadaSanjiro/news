package com.springbox.news.web.model;

import lombok.Data;

/**
 * Запрос на добалвение/изменение категории новостей
 */
@Data
public class UpsertNewsCategoryRequest {
    String name;
}
