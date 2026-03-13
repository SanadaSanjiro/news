package com.springbox.news.web.model;

import com.springbox.news.model.Comment;
import lombok.Data;

import java.time.Instant;
import java.util.List;

/**
 * DTO, возвращающий одну новость со списком ее комментариев
 */
@Data
public class NewsResponse {
    long id;
    UserResponse author;
    NewsCategoryResponse category;
    List<Comment> comments;         // поменять на CommentResponse!
    String title;
    String text;
    Instant createdAt;
    Instant updatedAt;
}
