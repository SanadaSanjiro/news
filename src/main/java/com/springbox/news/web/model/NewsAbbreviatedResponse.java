package com.springbox.news.web.model;

import lombok.Data;

/**
 * DTO для предоставления информации в списках новостей
 */
@Data
public class NewsAbbreviatedResponse {
    long id;
    UserShortResponse author;
    NewsCategoryShortResponse category;
    long commentCount;                   // содержит количество комментариев к данной новости
    String title;
    String text;
}
