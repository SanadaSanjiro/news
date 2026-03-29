package com.springbox.news.service;

import com.springbox.news.exception.EntityNotFoundException;
import com.springbox.news.model.User;
import com.springbox.news.repository.UserRepository;
import com.springbox.news.utils.BeanUtils;
import com.springbox.news.web.model.UserFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public Page<User> findAll(UserFilter filter) {
        return userRepository.findAll(
                PageRequest.of(filter.getPageNumber(), filter.getPageSize())
        );
    }

    @Override
    public User findById(long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        MessageFormat.format ("No user with id = {0} found", id)));
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        User existedUser = findById(user.getId());
        BeanUtils.copyNonNullProperties(user, existedUser);
        return userRepository.save(existedUser);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}