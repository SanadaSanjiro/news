package com.springbox.news.web.model;

import lombok.Data;

@Data
public class CommentShortResponse {
    long id;
    UserShortResponse commentAuthor;
    String text;
}
