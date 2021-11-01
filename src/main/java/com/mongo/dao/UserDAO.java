package com.mongo.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mongo.entity.User;

/**
 * @author Motasem Modallal
 *
 */
@Repository
public interface UserDAO extends MongoRepository<User, String> {

	/**
	 * Retrieve user info by userId.
	 * 
	 * @param userId
	 * @return
	 */
	User getUserByUserId(String userId);

}
