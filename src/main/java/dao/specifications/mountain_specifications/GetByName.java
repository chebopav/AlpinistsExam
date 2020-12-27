package dao.specifications.mountain_specifications;

import dao.specifications.Specification;
import entity.Mountain;
import entity.Mountain_;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class GetByName implements Specification<Mountain> {
    private String name;

    public GetByName(String name) {
        this.name = name;
    }

    @Override
    public Predicate getPredicate(Root<Mountain> root, CriteriaBuilder builder) {
        return builder.equal(root.get(Mountain_.name), name);
    }
}
