package com.springbox.news.web.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * Запрос на добалвение/изменение категории новостей
 */
@Data
public class UpsertNewsCategoryRequest {
    @NotBlank
    String name;
}
