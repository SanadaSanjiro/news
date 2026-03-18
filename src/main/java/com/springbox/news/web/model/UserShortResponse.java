package com.springbox.news.web.model;

import lombok.Data;

/**
 * DTO, возвращающий краткую информацию о пользователе
 */
@Data
public class UserShortResponse {
    long id;
    String name;
}
