package com.springbox.news.web.model;

import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * Запрос на добалвение/изменение категории новостей
 */
@Data
public class UpsertNewsCategoryRequest {
    @Size(min = 3, max = 25)
    String name;
}
