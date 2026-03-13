package com.springbox.news.web.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * DTO, возвращающий список из нескольких новостей (без комментариев)
 */
@Data
public class NewsListResponse {
    List<NewsShortResponse> news = new ArrayList<>();
}
