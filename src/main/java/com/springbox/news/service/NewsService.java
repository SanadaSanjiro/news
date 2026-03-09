package com.springbox.news.service;

import com.springbox.news.model.News;

import java.util.List;

/**
 * Сервис для работы с новостями
 */
public interface NewsService {
    /**
     * Получить список всех новостей
     * @return List<News> список со всеми новостями, либо пустой список, если ни одного пользователя не найдено
     */
    List<News> findAll();

    /**
     * Найти новость по ее id
     * @param id long id новости
     * @return News новость с заданным id
     */
    News findById(long id);

    /**
     * Сохранить новую новость
     * @param news News данные новости для сохранения
     * @return News сохраненную новость
     */
    News save(News news);

    /**
     * Изменить ранее сохраненную новость
     * @param news News данные новости для изменения
     * @return News измененную новость
     */
    News update(News news);

    /**
     * Удалить новость с заданным id
     * @param id long id новости, которую требуется удалить
     */
    void deleteById(long id);
}
