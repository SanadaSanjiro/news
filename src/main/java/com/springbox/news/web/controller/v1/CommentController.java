package com.springbox.news.web.controller.v1;

import com.springbox.news.mapper.v1.CommentMapperV1;
import com.springbox.news.model.Comment;
import com.springbox.news.service.CommentService;
import com.springbox.news.web.model.CommentListResponse;
import com.springbox.news.web.model.CommentResponse;
import com.springbox.news.web.model.UpsertCommentRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/comment")
@RequiredArgsConstructor
@Validated
public class CommentController {
    private final CommentService commentService;
    private final CommentMapperV1 commentMapper;

    @GetMapping("/")
    public ResponseEntity<CommentListResponse> findByNewsId(
            @RequestParam(value="newsid") @Min(0) long newsID) {
        return ResponseEntity.ok(
                commentMapper.commentListToCommentListResponse(commentService.findByNewsId(newsID))
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentResponse> findById(@PathVariable @Min(0) long id) {
        return ResponseEntity.ok(
                commentMapper.commentToResponse(commentService.findById(id))
        );
    }

    @PostMapping
    public ResponseEntity<CommentResponse> create(@RequestBody @Valid UpsertCommentRequest request) {
        Comment newComment = commentService.save(commentMapper.requestToComment(request));

        return ResponseEntity.status(HttpStatus.CREATED).body(
                commentMapper.commentToResponse(newComment)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentResponse> update(@PathVariable @Min(0) long id,
                                                  @RequestBody @Valid UpsertCommentRequest request) {
        Comment updatedComment = commentService.update(
                commentMapper.requestToComment(id, request)
        );

        return ResponseEntity.ok(commentMapper.commentToResponse(updatedComment));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable @Min(0) long id) {
        commentService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}