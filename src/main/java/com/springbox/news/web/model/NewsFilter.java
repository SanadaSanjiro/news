package com.springbox.news.web.model;

import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

/**
 * Фильтр новостей
 */
@Data
@NoArgsConstructor
public class NewsFilter {
    @Min(value = 0, message = "Номер страницы не может быть меньше нуля")
    int pageNumber;
    @Min(value = 1, message = "Размер страницы не может быть меньше единиц")
    int pageSize;
    Long userId;
    String category;
    String userName;
    String title;
    Instant createdBefore;
    Instant updatedBefore;
}
