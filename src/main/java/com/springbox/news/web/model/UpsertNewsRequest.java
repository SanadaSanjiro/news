package com.springbox.news.web.model;

import com.springbox.news.validation.Marker;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * Запрос на добавление/изменение новости
 */
@Data
public class UpsertNewsRequest {
    @Min(0)
    long authorId;
    @Min(value = 0, groups = Marker.OnCreate.class)
    long categoryId;
    @Size(min = 3, max = 50)
    @NotBlank
    String title;
    @Size(min = 3, max = 1000)
    @NotBlank
    String text;
}
