package com.springbox.news.repository;

import com.springbox.news.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findCommentsByNews_Id(long newsId);
}
