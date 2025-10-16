package com.sec.app.repo;

import com.sec.app.model.AppUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends MongoRepository<AppUser, String> {
    Optional<AppUser> findByEmail(String email);
    Optional<AppUser> findByUsername(String username);
}
