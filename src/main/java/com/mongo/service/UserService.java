package com.mongo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.mongo.dao.UserDAO;
import com.mongo.entity.User;

/**
 * @author Motasem Modallal
 *
 */
@Service
public class UserService {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private UserDAO userDAO;

	/**
	 * Retrieve Users Info By Ids.
	 * 
	 * @return
	 */
	public List<User> getAllUsers() {
		return mongoTemplate.findAll(User.class);
	}

	/**
	 * Retrieve user info by userId.
	 * 
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public User getUserById(String userId) throws Exception {
		if (userId == null) {
			throw new Exception("Null User");
		}
		return userDAO.getUserByUserId(userId);
	}

	/**
	 * add a new userd details.
	 * 
	 * @param userDTO
	 * @return
	 */
	public User addNewUser(User user) {
		mongoTemplate.save(user);
		return user;
	}

	/**
	 * update User details.
	 * 
	 * @param userDTO
	 * @return
	 */
	public User updateUser(User user) {
		mongoTemplate.save(user);
		return user;
	}

	/**
	 * delete User details.
	 * 
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public void deleteUser(String userId) throws Exception {
		if (userId == null) {
			throw new Exception("Null User");
		}
		userDAO.deleteById(userId);
	}

}
