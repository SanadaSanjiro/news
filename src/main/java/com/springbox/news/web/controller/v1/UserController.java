package com.springbox.news.web.controller.v1;

import com.springbox.news.mapper.v1.UserMapperV1;
import com.springbox.news.model.User;
import com.springbox.news.service.UserService;
import com.springbox.news.web.model.UpsertUserRequest;
import com.springbox.news.web.model.UserFilter;
import com.springbox.news.web.model.UserListResponse;
import com.springbox.news.web.model.UserResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@Validated
public class UserController {

    private final UserService userService;

    private final UserMapperV1 userMapper;

    @GetMapping
    public ResponseEntity<UserListResponse> findAll(@Valid UserFilter filter) {
        return ResponseEntity.ok(
                userMapper.userListToUserListResponse(
                        userService.findAll(filter).toList()
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable @Min(0) long id) {
        return ResponseEntity.ok(
                userMapper.userToResponse(
                        userService.findById(id)
                )
        );
    }

    @PostMapping
    public ResponseEntity<UserResponse> create(@RequestBody @Valid UpsertUserRequest request) {
        User newUser = userService.save(userMapper.requestToUser(request));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userMapper.userToResponse(newUser));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> update(@PathVariable @Min(0) long id,
                                               @RequestBody @Valid UpsertUserRequest request) {
        User uodatedUser = userService.update(userMapper.requestToUser(id, request));

        return ResponseEntity.ok(userMapper.userToResponse(uodatedUser));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable @Min(0) long id) {
        userService.delete(id);

        return ResponseEntity.noContent().build();
    }
}