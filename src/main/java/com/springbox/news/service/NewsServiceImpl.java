package com.springbox.news.service;

import com.springbox.news.exception.EntityNotFoundException;
import com.springbox.news.model.News;
import com.springbox.news.model.NewsCategory;
import com.springbox.news.model.User;
import com.springbox.news.repository.CategoryRepository;
import com.springbox.news.repository.NewsRepository;
import com.springbox.news.repository.UserRepository;
import com.springbox.news.utils.BeanUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService{
    private final NewsRepository repository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

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
        User author = getUser(news.getId());
        NewsCategory category = getCategory(news.getCategory().getId());
        news.setNewsAuthor(author);
        news.setCategory(category);
        return repository.save(news);
    }

    @Override
    public News update(News news) {
        User author = getUser(news.getId());
        NewsCategory category = getCategory(news.getCategory().getId());
        News existedNews = findById(news.getId());
        BeanUtils.copyNonNullProperties(news, existedNews);
        existedNews.setNewsAuthor(author);
        news.setCategory(category);
        return repository.save(existedNews);
    }

    @Override
    public void deleteById(long id) {
        repository.deleteById(id);
    }

    private User getUser(long id) {
        return userRepository.findById(id).orElseThrow(()->
                new EntityNotFoundException(
                        MessageFormat.format("Author with id = {0} not found", id)));
    }

    private NewsCategory getCategory(long id) {
        return categoryRepository.findById(id).orElseThrow(()->
                new EntityNotFoundException(
                        MessageFormat.format("News category with id = {0} not found", id)));
    }
}