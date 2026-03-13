package com.springbox.news.web.model;

import lombok.Data;

/**
 * Запрос на добавление/изменение новости
 */
@Data
public class UpsertNewsRequest {
    long authorId;
    long categoryId;
    String title;
    String text;
}
