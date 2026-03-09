package com.springbox.news.service;

import com.springbox.news.model.NewsCategory;

import java.util.List;

/**
 * Сервис для работы с категориями новостей
 */
public interface CategoryService {
    /**
     * Получить список всех категорий новостей
     * @return List<NewsCategory> список со всеми категориями новостей, либо пустой список, если таких еще нет
     */
    List<NewsCategory> findAll();

    /**
     * Найти категорию новостей по id
     * @param id long id категории новостей
     * @return категорию новостей с данным id
     */
    NewsCategory findById(long id);

    /**
     * Сохранить новую категорию новостей
     * @param category NewsCategory данные новой категории, которую требуется сохранить
     * @return данные сохраненной категории новостей
     */
    NewsCategory save(NewsCategory category);

    /**
     * Изменить данные ранее сохраненной категории новостей
     * @param category NewsCategory данные категории новостей для изменения
     * @return NewsCategory измененную категорию новостей
     */
    NewsCategory update(NewsCategory category);

    /**
     * Удалить категорию новостей по ее id
     * @param id long id категории новостей для удаления
     */
    void deleteById(long id);
}
