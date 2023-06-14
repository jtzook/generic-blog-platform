package com.jtzook.gbapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jtzook.gbapi.models.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    User findByEmail(String email);
}

