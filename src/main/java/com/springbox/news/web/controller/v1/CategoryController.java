package com.springbox.news.web.controller.v1;

import com.springbox.news.mapper.v1.CategoryMapperV1;
import com.springbox.news.model.NewsCategory;
import com.springbox.news.service.CategoryService;
import com.springbox.news.web.model.NewsCategoryFilter;
import com.springbox.news.web.model.NewsCategoryListResponse;
import com.springbox.news.web.model.NewsCategoryResponse;
import com.springbox.news.web.model.UpsertNewsCategoryRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
@Validated
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryMapperV1 categoryMapper;

    @GetMapping
    public ResponseEntity<NewsCategoryListResponse> findAll(@Valid NewsCategoryFilter filter) {

        return ResponseEntity.ok(
                categoryMapper.categoryListToNewsCategoryListResponse(
                        categoryService.findAll(filter).toList()
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<NewsCategoryResponse> findById(@PathVariable @Min(0) long id) {
        return ResponseEntity.ok(
                categoryMapper.categoryToResponse(categoryService.findById(id))
        );
    }

    @PostMapping()
    public ResponseEntity<NewsCategoryResponse> create(@RequestBody @Valid UpsertNewsCategoryRequest request) {
        NewsCategory newsCategory = categoryService.save(categoryMapper.requestToCategory(request));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(categoryMapper.categoryToResponse(newsCategory)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<NewsCategoryResponse> update(@PathVariable @Min(0) long id,
                                                       @RequestBody @Valid UpsertNewsCategoryRequest request) {

        NewsCategory updatedCategory = categoryService.update(categoryMapper.requestToCategory(id, request));

        return ResponseEntity.ok(categoryMapper.categoryToResponse(updatedCategory));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable @Min(0) long id) {
        categoryService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
