package com.springbox.news.web.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Фильтр списка пользователей
 */
@Data
@NoArgsConstructor
public class UserFilter {
    int pageNumber;
    int pageSize;
}
