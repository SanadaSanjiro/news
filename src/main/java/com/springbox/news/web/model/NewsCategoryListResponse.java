package com.springbox.news.web.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * DTO, возвращающий список новостных категорий
 */
@Data
public class NewsCategoryListResponse {
    private List<NewsCategoryResponse> categories = new ArrayList<>();
}
