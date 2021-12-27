package com.technospark.api.restapi.repository;

import com.technospark.api.restapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByUserId(String userId);
}
