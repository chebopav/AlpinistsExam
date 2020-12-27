package dao.specifications.mountain_specifications;

import dao.specifications.Specification;
import entity.Mountain;
import entity.Mountain_;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Objects;

public class GetByCountryName implements Specification<Mountain> {
    private String countryName;

    public GetByCountryName(String countryName) {
        this.countryName = Objects.requireNonNull(countryName);
    }

    @Override
    public Predicate getPredicate(Root<Mountain> root, CriteriaBuilder builder) {
        return builder.equal(root.get(Mountain_.country), countryName);
    }
}
