package com.springbox.news.web.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class NewsListResponse {
    List<NewsShortResponse> news = new ArrayList<>();
}
