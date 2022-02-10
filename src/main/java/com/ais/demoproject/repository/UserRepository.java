package com.ais.demoproject.repository;

import com.ais.demoproject.model.User;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String>{
    User findUserByuserName(String userName);
}





