package com.jtzook.gbapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jtzook.gbapi.auth.ERole;
import com.jtzook.gbapi.models.UserRole;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<UserRole, Long> {
    Optional<UserRole> findByName(ERole name);
}

