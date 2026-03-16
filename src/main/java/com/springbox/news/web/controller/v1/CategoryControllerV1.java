package com.springbox.news.web.controller.v1;

import com.springbox.news.mapper.v1.CategoryMapperV1;
import com.springbox.news.model.NewsCategory;
import com.springbox.news.service.CategoryService;
import com.springbox.news.web.model.NewsCategoryListResponse;
import com.springbox.news.web.model.NewsCategoryResponse;
import com.springbox.news.web.model.UpsertNewsCategoryRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryControllerV1 {
    private final CategoryService categoryService;
    private final CategoryMapperV1 categoryMapper;

    @GetMapping
    public ResponseEntity<NewsCategoryListResponse> findAll() {
        return ResponseEntity.ok(
                categoryMapper.categoryListToNewsCategoryListResponse(
                        categoryService.findAll()
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<NewsCategoryResponse> findById(@PathVariable long id) {
        return ResponseEntity.ok(
                categoryMapper.categoryToResponse(categoryService.findById(id))
        );
    }

    @PostMapping()
    public ResponseEntity<NewsCategoryResponse> create(@RequestBody UpsertNewsCategoryRequest request) {
        NewsCategory newsCategory = categoryService.save(categoryMapper.requestToCategory(request));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(categoryMapper.categoryToResponse(newsCategory)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<NewsCategoryResponse> update(@PathVariable long id,
                                                       @RequestBody UpsertNewsCategoryRequest request) {

        NewsCategory updatedCategory = categoryService.update(categoryMapper.requestToCategory(id, request));

        return ResponseEntity.ok(categoryMapper.categoryToResponse(updatedCategory));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        categoryService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
