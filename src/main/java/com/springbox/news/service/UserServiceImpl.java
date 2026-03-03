package com.springbox.news.service;

import com.springbox.news.model.User;
import com.springbox.news.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(long id) {
        return userRepository.findById(id).orElseThrow();
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        User oldUser = findById(user.getId());
        return userRepository.save(oldUser);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
