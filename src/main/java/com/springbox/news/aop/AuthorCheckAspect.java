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


/**
 * Класс предоставляет адвайсы, контролирующие, что изменение статьи или комментария
 * будет доступно лишь его автором. Пользователь определяется по хедеру X-User-Id
 */
@Aspect
@Component
@RequiredArgsConstructor
public class AuthorCheckAspect {
    private final CommentService commentService;
    private final NewsService newsService;

    /**
     * Адвайс, контролирующий обработку запросов на удаление комментариев в соответствующем контроллере.
     * Проверяет соответствие id пользователя из хедера X-User-Id автору комментария
     * @param id long ID комментария на удаление
     * @param currentUserId ID пользователя, отправившего запрос, берется из хедера  X-User-Id
     * @throws AccessDeniedException если id пользователя, отправившего запрос, не соответствует автору комментария,
     * выбрасывается исключение
     */
    @Before(value = "execution(* com.springbox.news.web.controller.v1.CommentController.delete(..))" +
            " && args(id, currentUserId)", argNames = "id,currentUserId")
    public void checkCommentBeforeDelete(long id, Long currentUserId) throws AccessDeniedException {
        Comment comment = commentService.findById(id);

        if (!(comment.getCommentAuthor().getId() == currentUserId)) {
            throw new AccessDeniedException("У вас нет прав на удаление этого комментария!");
        }
    }

    /**
     * Адвайс, контролирующий обработку запросов на изменение комментариев в соответствующем контроллере.
     * @param id long ID изменяемого комментария
     * @param currentUserId ID пользователя, отправившего запрос, берется из хедера X-User-Id
     * @throws AccessDeniedException если id пользователя, отправившего запрос, не соответствует автору комментария,
     * выбрасывается исключение
     */
    @Before(value = "execution(* com.springbox.news.web.controller.v1.CommentController.update(..))" +
            " && args(id, .., currentUserId)", argNames = "id,currentUserId")
    public void checkCommentBeforeUpdate(long id, Long currentUserId) throws AccessDeniedException {
        Comment comment = commentService.findById(id);

        if (!(comment.getCommentAuthor().getId() == currentUserId)) {
            throw new AccessDeniedException("У вас нет прав на изменение этого комментария!");
        }
    }

    /**
     * Адвайс, контролирующий обработку запросов на удаление новостей в соответствующем контроллере.
     * @param id long ID новости на удаление
     * @param currentUserId ID пользователя, отправившего запрос, берется из хедера X-User-Id
     * @throws AccessDeniedException если id пользователя, отправившего запрос, не соответствует автору новости,
     * выбрасывается исключение
     */
    @Before(value="execution(* com.springbox.news.web.controller.v1.NewsController.delete(..)) " +
            " && args(id, currentUserId)", argNames = "id, currentUserId")
    public void checkNewsBeforeDelete(long id, Long currentUserId) throws AccessDeniedException {
        News news = newsService.findById(id);

        if (!(news.getNewsAuthor().getId() == currentUserId)) {
            throw new AccessDeniedException("У вас нет прав на удаление этой новости!");
        }
    }

    /**
     * Адвайс, контролирующий обработку запросов на изменение новостей в соответствующем контроллере.
     * @param id long ID изменяемой новости
     * @param currentUserId ID пользователя, отправившего запрос, берется из хедера X-User-Id
     * @throws AccessDeniedException если id пользователя, отправившего запрос, не соответствует автору новости,
     * выбрасывается исключение
     */
    @Before(value="execution(* com.springbox.news.web.controller.v1.NewsController.update(..)) " +
            " && args(id, .., currentUserId)", argNames = "id, currentUserId")
    public void checkNewsBeforeUpdating(long id, Long currentUserId) throws AccessDeniedException {
        News news = newsService.findById(id);

        if (!(news.getNewsAuthor().getId() == currentUserId)) {
            throw new AccessDeniedException("У вас нет прав на изменение этой новости!");
        }
    }
}