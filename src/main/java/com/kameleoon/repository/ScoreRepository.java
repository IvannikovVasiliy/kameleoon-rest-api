package com.kameleoon.repository;

import com.kameleoon.entity.ScoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScoreRepository extends JpaRepository<ScoreEntity, Long> {
}
