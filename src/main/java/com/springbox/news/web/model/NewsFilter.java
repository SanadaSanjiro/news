package com.springbox.news.web.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

/**
 * Фильтр новостей
 */
@Data
@NoArgsConstructor
public class NewsFilter {
    int pageNumber;
    int pageSize;
    Long userId;
    String category;
    String userName;
    String title;
    Instant createdBefore;
    Instant updatedBefore;
}
