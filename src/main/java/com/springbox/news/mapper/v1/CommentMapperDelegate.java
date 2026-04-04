package com.springbox.news.mapper.v1;

import com.springbox.news.model.Comment;
import com.springbox.news.service.NewsService;
import com.springbox.news.service.UserService;
import com.springbox.news.web.model.UpsertCommentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Класс-делегат, предоставляющий методы для преобразования объектов UpsertCommentRequest в Comment,
 * требующие дополнительной обработки, не предоставляемой MapStruct
 */

@Component
public abstract class CommentMapperDelegate implements CommentMapperV1 {
    @Autowired
    private UserService userService;
    @Autowired
    private NewsService newsService;

    @Override
    public Comment requestToComment(UpsertCommentRequest request) {
        Comment comment = new Comment();
        comment.setText(request.getText());
        comment.setCommentAuthor(userService.findById(request.getUserId()));
        comment.setNews(newsService.findById(request.getNewsId()));
        return comment;
    }

    @Override
    public Comment requestToComment(long id, UpsertCommentRequest request) {
        Comment comment = requestToComment(request);
        comment.setId(id);
        return comment;
    }
}
