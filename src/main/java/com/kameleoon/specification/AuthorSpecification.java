package com.kameleoon.specification;

import com.kameleoon.model.Author;
import com.kameleoon.model.AuthorSearchCriteria;
import com.kameleoon.model.Author_;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class AuthorSpecification implements Specification<Author> {

    private AuthorSearchCriteria criteria;

    public AuthorSpecification(AuthorSearchCriteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<Author> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Predicate predicateName = criteriaBuilder.like(root.get(Author_.NAME), "%");
        Predicate predicateSurname = criteriaBuilder.like(root.get(Author_.SURNAME), "%");
        Predicate predicatePseudonym = criteriaBuilder.isNull(root.get(Author_.PSEUDONYM));

        if (criteria.getName() != null) {
            predicateName = criteriaBuilder.equal(root.get(Author_.NAME), criteria.getName());
        }
        if (criteria.getSurname() != null) {
            predicateSurname = criteriaBuilder.equal(root.get(Author_.SURNAME), criteria.getSurname());
        }
        if (criteria.getPseudonym() != null) {
            predicatePseudonym = criteriaBuilder.equal(root.get(Author_.PSEUDONYM), criteria.getPseudonym());
        }
        Predicate predicate = criteriaBuilder.and(predicateName, predicateSurname, predicatePseudonym);

        return predicate;
    }
}
