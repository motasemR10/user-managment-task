package com.mongo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mongo.entity.User;
import com.mongo.service.UserService;

/**
 * @author Motasem Modallal
 *
 */
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

	private final Logger LOG = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	/**
	 * Retrieve Users Info By Ids.
	 * 
	 * @return
	 */
	@GetMapping("/retrieveUsersInfoByIds")
	public List<User> getAllUsers() {
		LOG.info("Getting all users.");
		return userService.getAllUsers();
	}

	/**
	 * Retrieve user info by userId.
	 * 
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/{userId}/getUserDetails")
	public User getUserById(@PathVariable("userId") String userId) throws Exception {
		User user = userService.getUserById(userId);
		if (user != null) {
			LOG.info("Getting user with ID: {}.", userId);
			return user;
		} else {
			throw new Exception("user not exsit!");
		}
	}

	/**
	 * add a new userd details.
	 * 
	 * @param userDTO
	 * @return
	 */
	@PostMapping("/addNewUser")
	public User addNewUser(@RequestBody User user) {
		LOG.info("Saving user.");
		return userService.addNewUser(user);
	}

	/**
	 * update User details.
	 * 
	 * @param userId
	 * @param userDTO
	 * @return
	 * @throws Exception
	 */
	@PutMapping("/{userId}/updateUser")
	public User updateUser(@PathVariable("userId") String userId, @RequestBody User userBody) throws Exception {
		User user = userService.getUserById(userId);
		if (user != null) {
			LOG.info("update user.");
			user.setUserName(userBody.getUserName());
			user.setBirthDate(userBody.getBirthDate());
			user.setProfilePictureUrl(userBody.getProfilePictureUrl());
			return userService.updateUser(user);
		} else {
			throw new Exception("user not exsit!");
		}
	}

	/**
	 * delete User details.
	 * 
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@DeleteMapping("/{userId}/deleteUser")
	public String deleteUser(@PathVariable("userId") String userId) throws Exception {
		User user = userService.getUserById(userId);
		if (user != null) {
			LOG.info("Delete user.");
			userService.deleteUser(userId);
			return "SUCCESS";
		} else {
			throw new Exception("user not exsit!");
		}
	}

}