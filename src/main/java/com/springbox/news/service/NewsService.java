package com.springbox.news.service;

import com.springbox.news.model.News;
import com.springbox.news.web.model.NewsFilter;
import org.springframework.data.domain.Page;

/**
 * Сервис для работы с новостями
 */
public interface NewsService {
    /**
     * Получить страницу с новостями согласно переданным условиям фильтрации
     * @return Page<News> Объект Page со всеми новостями, отвечающими условиям фильтра
     * либо пустой список, если таких не нашлось.
     */
    Page<News> findAll(NewsFilter filter);

    /**
     * Найти новость по ее id
     * @param id long id новости
     * @return News новость с заданным id
     */
    News findById(long id);

    /**
     * Сохранить новость
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