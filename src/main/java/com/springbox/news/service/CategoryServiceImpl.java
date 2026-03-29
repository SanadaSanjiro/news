package com.springbox.news.service;

import com.springbox.news.exception.EntityNotFoundException;
import com.springbox.news.model.NewsCategory;
import com.springbox.news.repository.CategoryRepository;
import com.springbox.news.utils.BeanUtils;
import com.springbox.news.web.model.NewsCategoryFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{
    private final CategoryRepository repository;

    @Override
    public Page<NewsCategory> findAll(NewsCategoryFilter filter) {
        return repository.findAll(PageRequest.of(filter.getPageNumber(), filter.getPageSize()));
    }

    @Override
    public NewsCategory findById(long id) {
        return repository.findById(id).orElseThrow(()->
                new EntityNotFoundException(MessageFormat
                        .format("No news category with id = {0} found", id)));
    }

    @Override
    public NewsCategory save(NewsCategory category) {
        return repository.save(category);
    }

    @Override
    public NewsCategory update(NewsCategory category) {
        NewsCategory existedCategory = findById(category.getId());

        BeanUtils.copyNonNullProperties(category, existedCategory);
        return repository.save(existedCategory);
    }

    @Override
    public void deleteById(long id) {
        repository.deleteById(id);
    }
}