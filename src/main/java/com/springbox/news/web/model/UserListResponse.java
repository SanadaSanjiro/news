package com.springbox.news.web.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * DTO, возвращающий информацию о нескольких пользователях
 */
@Data
public class UserListResponse {
    private List<UserResponse> users = new ArrayList<>();
}
