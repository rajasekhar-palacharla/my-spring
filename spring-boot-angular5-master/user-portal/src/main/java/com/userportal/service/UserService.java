package com.userportal.service;

import java.util.List;

import com.userportal.model.User;

public interface UserService {

    User create(User user);

    User delete(long id);

    List<User> findAll();

    User findById(long id);

    User update(User user);
}
