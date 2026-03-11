package com.springbox.news.mapper.v1;

import com.springbox.news.model.News;
import com.springbox.news.service.CategoryService;
import com.springbox.news.service.UserService;
import com.springbox.news.web.model.UpsertNewsRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Класс-делегат, предоставляющий методы для преобразования объектов UpsertNewsRequest в News,
 * требующие дополнительной обработки, не предоставляемой MapStruct
 */
@Component
@RequiredArgsConstructor
public abstract class NewsMapperDelegate implements NewsMapperV1{
    private final UserService userService;
    private final CategoryService categoryService;

    @Override
    public News requestToNews(UpsertNewsRequest request) {
        News news = new News();
        news.setTitle(request.getTitle());
        news.setText(request.getText());
        news.setNewsAuthor(userService.findById(request.getAuthorId()));
        news.setCategory(categoryService.findById(request.getCategoryId()));
        return news;
    }

    @Override
    public News requestToNews(long id, UpsertNewsRequest request) {
        News news = requestToNews(request);
        news.setId(id);
        return news;
    }
}
