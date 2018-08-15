package com.userportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.userportal.model.User;
import com.userportal.service.UserService;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping({ "/users" })
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping
	public User create(@RequestBody User user) {
		return userService.create(user);
	}

	@GetMapping(path = { "/{id}" })
	public User findOne(@PathVariable("id") long id) {
		return userService.findById(id);
	}

	@PutMapping(path = { "/{id}" })
	public User update(@PathVariable("id") long id, @RequestBody User user) {
		user.setId(id);
		return userService.update(user);
	}

	@DeleteMapping(path = { "/{id}" })
	public User delete(@PathVariable("id") long id) {
		return userService.delete(id);
	}

	@GetMapping
	public List<User> findAll() {
		return userService.findAll();
	}
}
