package com.springbox.news.web.model;

import lombok.Data;

/**
 * DTO, содержащий краткую информацию о новости
 */
@Data
public class NewsShortResponse {
    long id;
    String title;
}
