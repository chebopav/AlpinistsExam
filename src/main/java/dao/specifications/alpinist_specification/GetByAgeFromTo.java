package dao.specifications.alpinist_specification;

import entity.Alpinist;
import entity.Alpinist_;
import dao.specifications.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class GetByAgeFromTo implements Specification<Alpinist> {

    private int from;
    private int to;

    public GetByAgeFromTo(int from, int to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public Predicate getPredicate(Root<Alpinist> root, CriteriaBuilder builder) {
        return builder.and(builder.ge(root.get(Alpinist_.age), from), builder.lt(root.get(Alpinist_.age), to));
    }
}
