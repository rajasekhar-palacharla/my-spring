package com.userportal.repository;

import org.springframework.data.repository.Repository;

import com.userportal.model.User;

import java.util.List;

public interface UserRepository extends Repository<User, Long> {

    void delete(User user);

    List<User> findAll();

    User findOne(long id);

    User save(User user);
}
