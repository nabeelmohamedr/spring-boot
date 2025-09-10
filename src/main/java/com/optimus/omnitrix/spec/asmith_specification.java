package com.optimus.omnitrix.spec;

import com.optimus.omnitrix.entity.asmith_entity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class asmith_specification {

    public static Specification<asmith_entity> hasPowerGreaterThan(int minPower) {
        return (root, query, cb) -> cb.greaterThan(root.get("power"), minPower);
    }

    public static Specification<asmith_entity> hasPowerLessThan(int maxPower) {
        return (root, query, cb) -> cb.lessThan(root.get("power"), maxPower);
    }

    public static Specification<asmith_entity> hasExactPower(int power) {
        return (root, query, cb) -> cb.equal(root.get("power"), power);
    }
}
