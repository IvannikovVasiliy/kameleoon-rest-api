package com.kameleoon.repository;

import com.kameleoon.entity.QuoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface QuoteRepository extends JpaRepository<QuoteEntity, Long>, JpaSpecificationExecutor<QuoteEntity> {
    @Query("select id from QuoteEntity order by random() limit 1")
    Long getRandomId();
}
