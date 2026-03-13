package com.springbox.news.web.model;

import lombok.Data;

import java.time.Instant;

/**
 * DTO, возвращающий информацию о пользователе
 */
@Data
public class UserResponse {
    long id;
    String name;
    Instant createdAt;
    Instant updatedAt;
}
