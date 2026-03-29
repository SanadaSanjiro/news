package com.springbox.news.mapper.v1;

import com.springbox.news.model.Comment;
import com.springbox.news.web.model.CommentListResponse;
import com.springbox.news.web.model.CommentResponse;
import com.springbox.news.web.model.CommentShortResponse;
import com.springbox.news.web.model.UpsertCommentRequest;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

/**
 * Предоставляет методы для преобразования объектов Comment в соответствующие DTO и обратно
 */
@DecoratedWith(CommentMapperDelegate.class)
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
uses = {UserMapperV1.class, NewsMapperV1.class})
public interface CommentMapperV1 {

    Comment requestToComment(UpsertCommentRequest request);

    Comment requestToComment(long id, UpsertCommentRequest request);

    CommentResponse commentToResponse(Comment comment);

    CommentShortResponse commentToShortResponse(Comment comment);

    List<CommentResponse> commentListToResponseList(List<Comment> comments);

    default CommentListResponse commentListToCommentListResponse(List<Comment> comments) {
        CommentListResponse response = new CommentListResponse();
        response.setComments(commentListToResponseList(comments));
        return  response;
    }
}
