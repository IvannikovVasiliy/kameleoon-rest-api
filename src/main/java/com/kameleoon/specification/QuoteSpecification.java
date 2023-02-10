package com.kameleoon.specification;

import com.kameleoon.model.QuoteEntity;
import com.kameleoon.model.Quote_;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class QuoteSpecification implements Specification<QuoteEntity> {
    private String content;

    public QuoteSpecification(String content) {
        this.content = content;
    }


    @Override
    public Predicate toPredicate(Root<QuoteEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Predicate predicateContent = criteriaBuilder.like(root.get(Quote_.CONTENT), "%");
        if (content != null) {
            predicateContent = criteriaBuilder.like(root.get(Quote_.CONTENT), "%" + content + "%");
        }

        Predicate predicate = criteriaBuilder.and(predicateContent);

        return predicate;
    }
}
