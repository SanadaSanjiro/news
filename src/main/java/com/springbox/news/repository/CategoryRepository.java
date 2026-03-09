package com.springbox.news.repository;

import com.springbox.news.model.NewsCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<NewsCategory, Long> {
}