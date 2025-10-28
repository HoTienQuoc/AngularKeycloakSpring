package com.keycloakAngularSpring.project.entity;

import org.springframework.data.jpa.domain.Specification;

public class BookSpecification {
    public static Specification<Book> withOwnerId(String ownerId){
        return (root, query, criteriabuilder) ->
            criteriabuilder.equal(root.get("owner").get("id"), ownerId)
        ;
    }
}
