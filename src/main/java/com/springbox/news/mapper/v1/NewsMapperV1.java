package com.springbox.news.mapper.v1;

import com.springbox.news.model.News;
import com.springbox.news.web.model.*;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

/**
 * Предоставляет методы для преобразования объектов News в соответствующие Response и обратно
 */
@DecoratedWith(NewsMapperDelegate.class)
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {UserMapperV1.class, CategoryMapperV1.class})
public interface NewsMapperV1 {

    /**
     * Преобразует запрос на создание новости в объект News
     * @param request UpsertNewsRequest
     * @return News
     */
    News requestToNews(UpsertNewsRequest request);

    /**
     * Преобразует запрос на создание новости в объект News с указанием его id
     * @param id long ID новости
     * @param request UpsertNewsRequest - DTO с новыми данными новости
     * @return News
     */
    @Mapping(source = "id", target = "id")
    News requestToNews(long id, UpsertNewsRequest request);

    /**
     * Преобразует новость в DTO с расширенной информацией о переданной новости
     * @param news News
     * @return NewsResponse
     */
    @Mapping(source = "newsAuthor", target = "author")
    NewsResponse newsToResponse(News news);

    /**
     * Создает DTO с укороченными данными новости (вместо списка комментариев возвращается
     * лишь их количество
     * @param news News новость, преобразуемая в DTO
     * @return NewsAbbreviatedResponse DTO с укороченными данными
     */
    NewsAbbreviatedResponse newsToAbbreviatedResponse(News news);

    /**
     * Преобразует новость в DTO с краткой информацией об этой новости
     * @param news объект типа News
     * @return NewsShortResponse DTO с краткой информацией
     */
    NewsShortResponse newsToShortResponse(News news);

    /**
     * Преобразует список новостей в список укороченных новостных DTO
     * @param news List<News>
     * @return List<NewsAbbreviatedResponse>
     */
    List<NewsAbbreviatedResponse> newsListToAbbreviatedResponseList(List<News> news);

    /**
     * Преобразует список новостей в DTO со списком укороченных новостных ответов
     * @param news List<News>
     * @return NewsListResponse
     */
    default NewsListResponse newsListToNewsListResponse(List<News> news) {
        System.out.println(news);
        NewsListResponse response = new NewsListResponse();
        response.setNews(newsListToAbbreviatedResponseList(news));
        return response;
    }
}
