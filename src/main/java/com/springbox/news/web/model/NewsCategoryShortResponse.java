package com.springbox.news.web.model;

import lombok.Data;

/**
 * DTO, возвращающий краткие данные новостной категории
 */
@Data
public class NewsCategoryShortResponse {
    String category;
}
