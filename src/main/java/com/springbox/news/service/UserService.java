package com.springbox.news.service;

import com.springbox.news.model.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User findById(long id);

    User save(User user);

    User update(User user);

    void delete(Long id);
}
