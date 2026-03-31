package com.springbox.news.web.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * Запрос на добалвение/изменение категории новостей
 */
@Data
public class UpsertNewsCategoryRequest {
    @NotEmpty
    String name;
}
