package com.springbox.news.web.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CommentListResponse {
    List<CommentResponse> comments = new ArrayList<>();
}
