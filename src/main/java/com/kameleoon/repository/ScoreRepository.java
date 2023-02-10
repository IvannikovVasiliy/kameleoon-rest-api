package com.kameleoon.repository;

import com.kameleoon.model.ScoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScoreRepository extends JpaRepository<ScoreEntity, Long> {
}
