package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.User;

/**
 * @author zhaokj
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
