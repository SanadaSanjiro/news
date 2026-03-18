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

    News requestToNews(UpsertNewsRequest request);

    @Mapping(source = "id", target = "id")
    News requestToNews(long id, UpsertNewsRequest request);

    @Mapping(source = "newsAuthor", target = "author")
    NewsResponse newsToResponse(News news);

    NewsAbbreviatedResponse newsToAbbreviatedResponse(News news);

    NewsShortResponse newsToShortResponse(News news);

    List<NewsAbbreviatedResponse> newsListToAbbreviatedResponseList(List<News> news);

    default NewsListResponse newsListToNewsListResponse(List<News> news) {
        System.out.println(news);
        NewsListResponse response = new NewsListResponse();
        response.setNews(newsListToAbbreviatedResponseList(news));
        return response;
    }
}
