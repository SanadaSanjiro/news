package com.springbox.news.mapper.v1;

import com.springbox.news.model.News;
import com.springbox.news.web.model.NewsListResponse;
import com.springbox.news.web.model.NewsResponse;
import com.springbox.news.web.model.NewsShortResponse;
import com.springbox.news.web.model.UpsertNewsRequest;
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

    @Mapping(source = "newsAuthor", target = "author")
    NewsShortResponse newsToShortResponse(News news);

    List<NewsShortResponse> newsListToShortResponseList(List<News> news);

    default NewsListResponse newsListToNewsListResponse(List<News> news) {
        NewsListResponse response = new NewsListResponse();
        response.setNews(newsListToShortResponseList(news));
        return response;
    }
}
