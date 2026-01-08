package com.cfc.JournalApp.repository;

import com.cfc.JournalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId> {
    User findUserByUsername(String username);

    void deleteUserByUsername(String username);
}
