package com.springbox.news.web.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
    @Min(0)
    Long userId;
    @Size(min = 3, max = 25)
    String category;
    @NotBlank
    String userName;
    @Size(min = 3, max = 50)
    String title;
    Instant createdBefore;
    Instant updatedBefore;
}
