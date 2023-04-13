package com.example.authdemo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.authdemo.model.UserInfo;

@Repository
public interface UserRepository extends JpaRepository<UserInfo, Integer>{

	Optional<UserInfo> findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
}
