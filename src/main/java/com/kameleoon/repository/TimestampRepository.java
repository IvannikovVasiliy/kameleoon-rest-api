package com.kameleoon.repository;

import com.kameleoon.model.Timestamps;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimestampRepository extends JpaRepository<Timestamps, Long> {
}
