package com.springbox.news.config;

import com.springbox.news.exception.EntityNotFoundException;
import com.springbox.news.model.Comment;
import com.springbox.news.model.News;
import com.springbox.news.model.NewsCategory;
import com.springbox.news.model.User;
import com.springbox.news.service.CategoryService;
import com.springbox.news.service.CommentService;
import com.springbox.news.service.NewsService;
import com.springbox.news.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBooleanProperty;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * Вносит в БД тестовые данные, если настройка app.config.testdata.enabled = true
 */

@Component
@ConditionalOnBooleanProperty(name = "app.config.testdata.enabled")
@RequiredArgsConstructor
public class TestData {
    private final CategoryService categoryService;
    private final UserService userService;
    private final NewsService newsService;
    private final CommentService commentService;

    @EventListener(ContextRefreshedEvent.class)
    public void addTestData() {
        User firstUser = null;
        try {
            firstUser = userService.findById(1);
        } catch (EntityNotFoundException e) {
            System.out.println("User table not empty, skipping test data creation");
        }
        if (Objects.isNull(firstUser)){
            for (int i = 1; i < 10; i++) {
                NewsCategory category = createNewsCategory(i);
                User user = createUser(i);
                News news = createNews(i, user, category);
                createComment(i, user, news);
            }
        }
    }

    private NewsCategory createNewsCategory(int categoryNumber) {
        NewsCategory category = new NewsCategory();
        category.setName("Category " + categoryNumber);
        return categoryService.save(category);
    }

    private User createUser(int userNumber) {
        User user = new User();
        user.setName("User " + userNumber);
        return userService.save(user);
    }

    private News createNews(int newsNumber, User author, NewsCategory category) {
        News news = new News();
        news.setNewsAuthor(author);
        news.setCategory(category);
        news.setTitle("Title" + newsNumber);
        news.setText("This is news number " + newsNumber);
        return newsService.save(news);
    }

    private void createComment(int commentNumber, User author, News news) {
        Comment comment = new Comment();
        comment.setCommentAuthor(author);
        comment.setNews(news);
        comment.setText("This is comment number " + commentNumber);
        commentService.save(comment);
    }
}
