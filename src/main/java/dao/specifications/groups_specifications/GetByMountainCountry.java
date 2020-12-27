package dao.specifications.groups_specifications;

import dao.specifications.Specification;
import entity.Mountain;
import entity.TouristGroup;
import entity.TouristGroup_;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class GetByMountainCountry implements Specification<TouristGroup> {
    private Mountain mountain;

    public GetByMountainCountry(Mountain mountain) {
        this.mountain = mountain;
    }

    @Override
    public Predicate getPredicate(Root<TouristGroup> root, CriteriaBuilder builder) {
        return builder.equal(root.get(TouristGroup_.mountain), mountain);
    }
}
