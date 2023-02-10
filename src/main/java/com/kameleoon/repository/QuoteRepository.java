package com.kameleoon.repository;

import com.kameleoon.model.QuoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface QuoteRepository extends JpaRepository<QuoteEntity, Long>, JpaSpecificationExecutor<QuoteEntity> {
    @Query("select count(*) from QuoteEntity")
    Long getCount();
}
