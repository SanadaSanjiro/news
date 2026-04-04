package com.springbox.news.web.model;

import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Фильтр списка пользователей
 */
@Data
@NoArgsConstructor
public class UserFilter {
    @Min(value = 0, message = "Номер страницы не может быть меньше нуля")
    int pageNumber;
    @Min(value = 1, message = "Размер страницы не может быть меньше единицы")
    int pageSize;
}
