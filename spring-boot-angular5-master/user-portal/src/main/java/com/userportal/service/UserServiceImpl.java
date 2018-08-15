package com.userportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.userportal.model.User;
import com.userportal.repository.UserRepository;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;
    
    @Autowired
    private StorageService storageService;
    

    @Override
    public User create(User user) {
        return repository.save(user);
    }

    @Override
    public User delete(long id) {
        User user = findById(id);
        if(user != null){
            repository.delete(user);
            storageService.delete(user.getId());
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public User findById(long id) {
    	try {
        return repository.findOne(id);
    	}
    	catch (Exception e) {
    		e.printStackTrace();
			return null;
		}
    }

    @Override
    public User update(User user) {
        return repository.save(user);
    }
}
