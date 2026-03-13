package com.springbox.news.web.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * DTO, возвращающий список комментариев
 */
@Data
public class CommentListResponse {
    List<CommentResponse> comments = new ArrayList<>();
}
