package com.springbox.news.web.model;

import lombok.Data;

import java.time.Instant;

/**
 * DTO, возвращающий новостную категорию
 */
@Data
public class NewsCategoryResponse {
    long id;
    String name;
    Instant createdAt;
    Instant updatedAt;
}
