package com.springbox.news.web.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class NewsCategoryListResponse {
    private List<NewsCategoryResponse> categories = new ArrayList<>();
}
