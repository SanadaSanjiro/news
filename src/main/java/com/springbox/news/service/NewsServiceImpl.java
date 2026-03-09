package com.springbox.news.service;

import com.springbox.news.exception.EntityNotFoundException;
import com.springbox.news.model.News;
import com.springbox.news.repository.NewsRepository;
import com.springbox.news.utils.BeanUtils;
import lombok.RequiredArgsConstructor;

import java.text.MessageFormat;
import java.util.List;

@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService{
    private final NewsRepository repository;

    @Override
    public List<News> findAll() {
        return repository.findAll();
    }

    @Override
    public News findById(long id) {
        return repository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException(
                        MessageFormat.format("No news with id = {0} found", id)));
    }

    @Override
    public News save(News news) {
        return repository.save(news);
    }

    @Override
    public News update(News news) {
        News existedNews = findById(news.getId());
        BeanUtils.copyNonNullProperties(news, existedNews);
        return repository.save(existedNews);
    }

    @Override
    public void deleteById(long id) {
        repository.deleteById(id);
    }
}