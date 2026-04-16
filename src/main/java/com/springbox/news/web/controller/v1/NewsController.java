package com.springbox.news.web.controller.v1;

import com.springbox.news.mapper.v1.NewsMapperV1;
import com.springbox.news.model.News;
import com.springbox.news.service.NewsService;
import com.springbox.news.validation.Marker;
import com.springbox.news.web.model.NewsFilter;
import com.springbox.news.web.model.NewsListResponse;
import com.springbox.news.web.model.NewsResponse;
import com.springbox.news.web.model.UpsertNewsRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.groups.Default;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/news")
@RequiredArgsConstructor
@Validated
public class NewsController {
    private final NewsService newsService;
    private final NewsMapperV1 newsMapper;

    @GetMapping
    public ResponseEntity<NewsListResponse> findAll(@Valid NewsFilter filter) {
        return ResponseEntity.ok(newsMapper.newsListToNewsListResponse(
                newsService.findAll(filter).toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<NewsResponse> findById(@PathVariable @Min(0) long id) {
        return ResponseEntity.ok(newsMapper.newsToResponse(newsService.findById(id)));
    }

    @PostMapping
    @Validated({Marker.OnUpdate.class, Default.class})
    public ResponseEntity<NewsResponse> create(@RequestBody @Valid UpsertNewsRequest request) {
        News news = newsService.save(newsMapper.requestToNews(request));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(newsMapper.newsToResponse(news));
    }

    @PutMapping("/{id}")
    @Validated({Marker.OnUpdate.class, Default.class})
    public ResponseEntity<NewsResponse> update(@PathVariable @Min(0) long id,
                                               @RequestBody @Valid UpsertNewsRequest request,
                                               @RequestHeader("X-User-Id") Long currentUserId) {
        News updatedNews = newsService.update(newsMapper.requestToNews(id, request));

        return ResponseEntity.ok(newsMapper.newsToResponse(updatedNews));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable @Min(0) long id,
                                       @RequestHeader("X-User-Id") Long currentUserId) {
        newsService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}