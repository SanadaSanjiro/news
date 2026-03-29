package com.springbox.news.service;

import com.springbox.news.model.User;
import com.springbox.news.web.model.UserFilter;
import org.springframework.data.domain.Page;

/**
 * Сервис для работы с данными пользователей
 */
public interface UserService {
    /**
     * Получить список всех пользователей
     * @return Page<User> список со всеми пользователями, либо пустой список, если ни одного пользователя не найдено
     */
    Page<User> findAll(UserFilter filter);

    /**
     * Найти пользователя по id
     * @param id long is - идентификатор пользователя
     * @return User данные пользователя, если он найден
     */
    User findById(long id);

    /**
     * Сохранить нового пользователя
     * @param user User данные нового пользователя для сохранения
     * @return User данные сохраненного пользователя
     */
    User save(User user);

    /**
     * Изменить данные ранее сохраненного пользователя
     * @param user User данные нового пользователя для изменения
     * @return User данные измененного пользователя
     */
    User update(User user);

    /**
     * Удалить пользователя по его id
     * @param id Long id пользователя
     */
    void delete(Long id);
}