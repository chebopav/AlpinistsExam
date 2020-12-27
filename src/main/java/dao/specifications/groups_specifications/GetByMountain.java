package dao.specifications.groups_specifications;

import dao.specifications.Specification;
import entity.Mountain;
import entity.TouristGroup;
import entity.TouristGroup_;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Objects;

public class GetByMountain implements Specification<TouristGroup> {

    private Mountain mountain;

    public GetByMountain(Mountain mountain) {
        this.mountain = Objects.requireNonNull(mountain);
    }

    @Override
    public Predicate getPredicate(Root<TouristGroup> root, CriteriaBuilder builder) {
        return builder.equal(root.get(TouristGroup_.mountain), mountain);
    }
}
