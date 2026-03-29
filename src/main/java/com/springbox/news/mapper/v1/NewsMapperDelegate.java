package com.springbox.news.mapper.v1;

import com.springbox.news.model.News;
import com.springbox.news.service.CategoryService;
import com.springbox.news.service.UserService;
import com.springbox.news.web.model.NewsAbbreviatedResponse;
import com.springbox.news.web.model.NewsCategoryShortResponse;
import com.springbox.news.web.model.UpsertNewsRequest;
import com.springbox.news.web.model.UserShortResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Класс-делегат, предоставляющий методы для преобразования объектов UpsertNewsRequest в News,
 * требующие дополнительной обработки, не предоставляемой MapStruct
 */
@Component
public abstract class NewsMapperDelegate implements NewsMapperV1{
    @Autowired
    private UserService userService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private UserMapperV1 userMapper;
    @Autowired
    private CategoryMapperV1 categoryMapper;


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

    @Override
    public NewsAbbreviatedResponse newsToAbbreviatedResponse(News news) {
        NewsAbbreviatedResponse response = new NewsAbbreviatedResponse();

        response.setId(news.getId());
        UserShortResponse author = userMapper.userToShortResponse(news.getNewsAuthor());
        response.setAuthor(author);

        NewsCategoryShortResponse category = categoryMapper
                .categoryToShortResponse(news.getCategory());
        response.setCategory(category);

        response.setTitle(news.getTitle());
        response.setText(news.getText());

        response.setCommentCount(news.getComments().size());

        return response;
    }

    @Override
    public List<NewsAbbreviatedResponse> newsListToAbbreviatedResponseList(List<News> news) {
        return news.stream()
                .map(this::newsToAbbreviatedResponse)
                .toList();
    }
}