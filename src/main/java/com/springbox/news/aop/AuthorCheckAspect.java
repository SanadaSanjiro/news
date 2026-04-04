package com.springbox.news.aop;

import com.springbox.news.model.Comment;
import com.springbox.news.service.CommentService;
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

    @Before(value = "execution(* com.springbox.news.web.controller.v1.CommentController.delete(..))" +
            " && args(id, currentUserId)", argNames = "id,currentUserId")
    public void checkBeforeDelete(long id, Long currentUserId) throws AccessDeniedException {
        Comment comment = commentService.findById(id);

        if (!(comment.getCommentAuthor().getId() == currentUserId)) {
            throw new AccessDeniedException("У вас нет прав на удаление этого комментария!");
        }
    }

    @Before(value = "execution(* com.springbox.news.web.controller.v1.CommentController.update(..))" +
            " && args(id, .., currentUserId)", argNames = "id,currentUserId")
    public void checkBeforeUpdating(long id, Long currentUserId) throws AccessDeniedException {
        Comment comment = commentService.findById(id);

        if (!(comment.getCommentAuthor().getId() == currentUserId)) {
            throw new AccessDeniedException("У вас нет прав на изменение этого комментария!");
        }
    }
}
