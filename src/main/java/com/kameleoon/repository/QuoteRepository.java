package com.kameleoon.repository;

import com.kameleoon.model.Quote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuoteRepository extends JpaRepository<Quote, Long> {
    Quote findByContent(String content);
}
