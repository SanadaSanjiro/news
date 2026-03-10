package com.springbox.news.web.model;

import com.springbox.news.model.NewsCategory;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class NewsCategoryListResponse {
    private List<NewsCategory> categories = new ArrayList<>();
}
