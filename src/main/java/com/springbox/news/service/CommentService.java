package com.springbox.news.service;

import com.springbox.news.model.Comment;

import java.util.List;

/**
 * Сервис для работы с комментариями
 */
public interface CommentService {
    /**
     * Получить список комментариев по id новости
     * @return List<Comment> список комментариев к заданной новости
     */
    List<Comment> findByNewsId(long newsId);

    /**
     * Найти комментарий по его id
     * @param id long id комментария
     * @return Comment комментарий с заданным id
     */
    Comment findById(long id);

    /**
     * Сохранить новый комментарий
     * @param comment Comment комментарий, который требуется сохранить
     * @return Comment данные сохраненного комментария
     */
    Comment save(Comment comment);

    /**
     * Обновить сохраненный ранее комментарий
     * @param comment Comment новые данные комментария
     * @return Comment обновленный комментарий
     */
    Comment update(Comment comment);

    /**
     * Удалить комментарий по его id
     * @param id long id комментария для удаления
     */
    void deleteById(long id);
}
