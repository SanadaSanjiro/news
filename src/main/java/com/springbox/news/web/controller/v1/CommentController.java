package com.springbox.news.web.controller.v1;

import com.springbox.news.mapper.v1.CommentMapperV1;
import com.springbox.news.model.Comment;
import com.springbox.news.service.CommentService;
import com.springbox.news.web.model.CommentListResponse;
import com.springbox.news.web.model.CommentResponse;
import com.springbox.news.web.model.UpsertCommentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private final CommentMapperV1 commentMapper;

    @GetMapping("/")
    public ResponseEntity<CommentListResponse> findByNewsId(
            @RequestParam(value="newsid") long newsID) {
        System.out.println(newsID);
        return ResponseEntity.ok(
                commentMapper.commentListToCommentListResponse(commentService.findByNewsId(newsID))
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentResponse> findById(@PathVariable long id) {
        System.out.println("Get method called");
        return ResponseEntity.ok(
                commentMapper.commentToResponse(commentService.findById(id))
        );
    }

    @PostMapping
    public ResponseEntity<CommentResponse> create(@RequestBody UpsertCommentRequest request) {
        Comment newComment = commentService.save(commentMapper.requestToComment(request));

        return ResponseEntity.status(HttpStatus.CREATED).body(
                commentMapper.commentToResponse(newComment)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentResponse> update(@PathVariable long id,
                                                  @RequestBody UpsertCommentRequest request) {
        Comment updatedComment = commentMapper.requestToComment(id, request);

        return ResponseEntity.ok(commentMapper.commentToResponse(updatedComment));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        commentService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
