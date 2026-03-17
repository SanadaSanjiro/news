package com.springbox.news.service;

import com.springbox.news.exception.EntityNotFoundException;
import com.springbox.news.model.Comment;
import com.springbox.news.model.News;
import com.springbox.news.model.User;
import com.springbox.news.repository.CommentRepository;
import com.springbox.news.repository.NewsRepository;
import com.springbox.news.repository.UserRepository;
import com.springbox.news.utils.BeanUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{
    private final CommentRepository repository;
    private final NewsRepository newsRepository;
    private final UserRepository userRepository;

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
        User existedUser = getUser(comment.getComment_author().getId());
        News existedNews = getNews(comment.getNews().getId());
        comment.setComment_author(existedUser);
        comment.setNews(existedNews);
        return repository.save(comment);
    }

    @Override
    public Comment update(Comment comment) {
        Comment existedComment = findById(comment.getId());
        User existedUser = getUser(comment.getComment_author().getId());
        News existedNews = getNews(comment.getNews().getId());
        BeanUtils.copyNonNullProperties(comment, existedComment);
        comment.setComment_author(existedUser);
        comment.setNews(existedNews);
        return repository.save(existedComment);
    }

    @Override
    public void deleteById(long id) {
        repository.deleteById(id);
    }

    private User getUser(long id) {
        return userRepository.findById(id).orElseThrow(()->
                new EntityNotFoundException(
                        MessageFormat.format("Author with id = {0} not found", id)));
    }

    private News getNews(long id) {
        return newsRepository.findById(id).orElseThrow(()->
                new EntityNotFoundException(
                        MessageFormat.format("News with id = {0} not found", id)));
    }
}