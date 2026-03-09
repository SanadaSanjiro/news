package com.springbox.news.service;

import com.springbox.news.exception.EntityNotFoundException;
import com.springbox.news.model.Comment;
import com.springbox.news.repository.CommentRepository;
import com.springbox.news.utils.BeanUtils;
import lombok.RequiredArgsConstructor;

import java.text.MessageFormat;
import java.util.List;

@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{
    private CommentRepository repository;

    @Override
    public List<Comment> findByNews(long newsId) {
        return repository.findAll();
    }

    @Override
    public Comment findById(long id) {
        return repository.findById(id).orElseThrow(()->
                new EntityNotFoundException(
                        MessageFormat.format("No comment with id = {0} found", id)
                ));
    }

    @Override
    public Comment save(Comment comment) {
        return repository.save(comment);
    }

    @Override
    public Comment update(Comment comment) {
        Comment existedComment = findById(comment.getId());

        BeanUtils.copyNonNullProperties(comment, existedComment);
        return repository.save(existedComment);
    }

    @Override
    public void deleteById(long id) {
        repository.deleteById(id);
    }
}