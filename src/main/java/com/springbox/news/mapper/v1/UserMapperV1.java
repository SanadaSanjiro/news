package com.springbox.news.mapper.v1;

import com.springbox.news.model.User;
import com.springbox.news.web.model.UpsertUserRequest;
import com.springbox.news.web.model.UserListResponse;
import com.springbox.news.web.model.UserResponse;
import com.springbox.news.web.model.UserShortResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

/**
 * Предоставляет методы для преобразования объектов User в соответствующие Response и обратно
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapperV1 {
    User requestToUser(UpsertUserRequest request);

    @Mapping(source = "userId", target = "id")
    User requestToUser(long userId, UpsertUserRequest request);

    UserResponse userToResponse(User user);

    UserShortResponse userToShortResponse(User user);

    List<UserResponse> userListToResponseList(List<User> users);

    default UserListResponse userListToUserListResponse(List<User> users) {
        UserListResponse response = new UserListResponse();
        response.setUsers(userListToResponseList(users));
        return response;
    }
}
