package com.springbox.news.repository;

import com.springbox.news.model.NewsCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CategoryRepository extends JpaRepository<NewsCategory, Long>,
        JpaSpecificationExecutor<NewsCategory> {
}