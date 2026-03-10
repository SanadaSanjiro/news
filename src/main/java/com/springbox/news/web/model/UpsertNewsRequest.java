package com.springbox.news.web.model;

import lombok.Data;

@Data
public class UpsertNewsRequest {
    long authorId;
    long categoryId;
    String title;
    String text;
}
