package com.springbox.news.aop;

import com.springbox.news.model.Comment;
import com.springbox.news.model.News;
import com.springbox.news.service.CommentService;
import com.springbox.news.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.nio.file.AccessDeniedException;

@Aspect
@Component
@RequiredArgsConstructor
public class AuthorCheckAspect {
    private final CommentService commentService;
    private final NewsService newsService;

    @Before(value = "execution(* com.springbox.news.web.controller.v1.CommentController.delete(..))" +
            " && args(id, currentUserId)", argNames = "id,currentUserId")
    public void checkCommentBeforeDelete(long id, Long currentUserId) throws AccessDeniedException {
        Comment comment = commentService.findById(id);

        if (!(comment.getCommentAuthor().getId() == currentUserId)) {
            throw new AccessDeniedException("У вас нет прав на удаление этого комментария!");
        }
    }

    @Before(value = "execution(* com.springbox.news.web.controller.v1.CommentController.update(..))" +
            " && args(id, .., currentUserId)", argNames = "id,currentUserId")
    public void checkCommentBeforeUpdate(long id, Long currentUserId) throws AccessDeniedException {
        Comment comment = commentService.findById(id);

        if (!(comment.getCommentAuthor().getId() == currentUserId)) {
            throw new AccessDeniedException("У вас нет прав на изменение этого комментария!");
        }
    }

    @Before(value="execution(* com.springbox.news.web.controller.v1.NewsController.delete(..)) " +
            " && args(id, currentUserId)", argNames = "id, currentUserId")
    public void checkNewsBeforeDelete(long id, Long currentUserId) throws AccessDeniedException {
        News news = newsService.findById(id);

        if (!(news.getNewsAuthor().getId() == currentUserId)) {
            throw new AccessDeniedException("У вас нет прав на удаление этой новости!");
        }
    }

    @Before(value="execution(* com.springbox.news.web.controller.v1.NewsController.update(..)) " +
            " && args(id, .., currentUserId)", argNames = "id, currentUserId")
    public void checkNewsBeforeUpdating(long id, Long currentUserId) throws AccessDeniedException {
        News news = newsService.findById(id);

        if (!(news.getNewsAuthor().getId() == currentUserId)) {
            throw new AccessDeniedException("У вас нет прав на изменение этой новости!");
        }
    }
}
