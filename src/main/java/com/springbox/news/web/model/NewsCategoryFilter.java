package com.springbox.news.web.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Фильтр новостных категорий
 */
@Data
@NoArgsConstructor
public class NewsCategoryFilter {
    int pageNumber;
    int pageSize;
}
