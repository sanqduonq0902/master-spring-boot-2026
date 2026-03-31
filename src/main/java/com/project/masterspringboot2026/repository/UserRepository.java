package com.project.masterspringboot2026.repository;

import com.project.masterspringboot2026.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    boolean existsByUsername(String username);
}
