package com.project.masterspringboot2026.repository;

import com.project.masterspringboot2026.model.InvalidToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvalidTokenRepository extends JpaRepository<InvalidToken, String> {
}
