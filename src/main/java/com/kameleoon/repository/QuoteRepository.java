package com.kameleoon.repository;

import com.kameleoon.model.QuoteTimeStamp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuoteRepository extends JpaRepository<QuoteTimeStamp, Long> {
}
