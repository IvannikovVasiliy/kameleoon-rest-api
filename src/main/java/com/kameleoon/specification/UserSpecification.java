package com.kameleoon.specification;

import com.kameleoon.model.UserEntity;
import com.kameleoon.model.UserEntity_;
import com.kameleoon.model.User_;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecification implements Specification<UserEntity> {

    private UserSearchCriteria criteria;

    public UserSpecification(UserSearchCriteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<UserEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Predicate predicateEmail = criteriaBuilder.like(root.get(UserEntity_.EMAIL), "%");
        Predicate predicateLogin = criteriaBuilder.like(root.get(UserEntity_.LOGIN), "%");

        if (criteria.getEmail() != null) {
            predicateEmail = criteriaBuilder.like(root.get(UserEntity_.EMAIL), "%" + criteria.getEmail() + "%");
        }
        if (criteria.getLogin() != null) {
            predicateLogin = criteriaBuilder.like(root.get(UserEntity_.LOGIN), "%" + criteria.getLogin() + "%");
        }
        Predicate predicate = criteriaBuilder.and(predicateEmail, predicateLogin);

        return predicate;
    }
}
