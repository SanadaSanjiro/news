package com.springbox.news.repository;

import com.springbox.news.model.News;
import com.springbox.news.web.model.NewsFilter;
import org.springframework.data.jpa.domain.Specification;

import java.time.Instant;
import java.util.Objects;

/**
 * Спецификация для фильтрации запросов по новостям
 */
public interface NewsSpecification {

    static Specification<News> withFilter (NewsFilter newsFilter) {
        return Specification.allOf(byUserId(newsFilter.getUserId()))
                .and(byCategory(newsFilter.getCategory()))
                .and(byUserName(newsFilter.getUserName()))
                .and(byTitle(newsFilter.getTitle()))
                .and(byCreatedAtBefore(newsFilter.getCreatedBefore()))
                .and(byUpdatedAtBefore(newsFilter.getUpdatedBefore()));
    }

    static Specification<News> byUserId(Long userId) {
        return ((root, query, criteriaBuilder) -> {
            if (Objects.isNull(userId)) {
                return null;
            }

            return criteriaBuilder.equal(root.get("newsAuthor").get("id"), userId);
        });
    }


    static Specification<News> byCategory(String category) {
        return ((root, query, criteriaBuilder) -> {
            if (Objects.isNull(category)) {
                return null;
            }

            return criteriaBuilder.equal(root.get("category").get("name"), category);
        });
    }

    static Specification<News> byUserName(String userName) {
        return ((root, query, criteriaBuilder) -> {
            if (Objects.isNull(userName)) {
                return null;
            }

            return criteriaBuilder.equal(root.get("newsAuthor").get("name"), userName);
        });
    }

    static Specification<News> byTitle(String title) {
        return ((root, query, criteriaBuilder) -> {
            if (Objects.isNull(title)) {
                return null;
            }

            return criteriaBuilder.equal(root.get("title"), title);
        });
    }

    static Specification<News> byCreatedAtBefore(Instant createdBefore) {
        return ((root, query, criteriaBuilder) -> {
            if (Objects.isNull(createdBefore)) {
                return null;
            }

            return criteriaBuilder.lessThanOrEqualTo(root.get("createdAt"), createdBefore);
        });
    }


    static Specification<News> byUpdatedAtBefore(Instant updatedBefore) {
        return ((root, query, criteriaBuilder) -> {
            if (Objects.isNull(updatedBefore)) {
                return null;
            }

            return criteriaBuilder.lessThanOrEqualTo(root.get("updatedAt"), updatedBefore);
        });
    }
}