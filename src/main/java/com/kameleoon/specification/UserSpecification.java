package com.kameleoon.specification;

import com.kameleoon.model.UserSearchCriteria;
import com.kameleoon.model.User;
import com.kameleoon.model.User_;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecification implements Specification<User> {

    private UserSearchCriteria criteria;

    public UserSpecification(UserSearchCriteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Predicate predicateEmail = criteriaBuilder.like(root.get(User_.EMAIL), "%");
        Predicate predicateLogin = criteriaBuilder.like(root.get(User_.LOGIN), "%");

        if (criteria.getEmail() != null) {
            predicateEmail = criteriaBuilder.equal(root.get(User_.EMAIL), criteria.getEmail());
        }
        if (criteria.getLogin() != null) {
            predicateLogin = criteriaBuilder.equal(root.get(User_.LOGIN), criteria.getLogin());
        }
        Predicate predicate = criteriaBuilder.and(predicateEmail, predicateLogin);

        return predicate;
    }
}
