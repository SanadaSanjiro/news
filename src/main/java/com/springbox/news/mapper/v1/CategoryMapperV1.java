package com.springbox.news.mapper.v1;

import com.springbox.news.model.NewsCategory;
import com.springbox.news.web.model.NewsCategoryListResponse;
import com.springbox.news.web.model.NewsCategoryResponse;
import com.springbox.news.web.model.NewsCategoryShortResponse;
import com.springbox.news.web.model.UpsertNewsCategoryRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

/**
 * Предоставляет методы для преобразования объектов NewsCategory в соответствующие Response и обратно
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapperV1 {

    NewsCategory requestToCategory (UpsertNewsCategoryRequest request);

    @Mapping(source = "id", target = "id")
    NewsCategory requestToCategory (long id, UpsertNewsCategoryRequest request);

    NewsCategoryResponse categoryToResponse(NewsCategory category);

    @Mapping(source = "name", target = "category")
    NewsCategoryShortResponse categoryToShortResponse(NewsCategory category);

    List<NewsCategoryResponse> categoryListToResponseList(List<NewsCategory> categories);

    default NewsCategoryListResponse categoryListToNewsCategoryListResponse(List<NewsCategory> categories) {
        NewsCategoryListResponse response = new NewsCategoryListResponse();
        response.setCategories(categoryListToResponseList(categories));
        return response;
    }
}
