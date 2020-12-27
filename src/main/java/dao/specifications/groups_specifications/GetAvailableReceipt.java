package dao.specifications.groups_specifications;

import dao.specifications.Specification;
import entity.TouristGroup;
import entity.TouristGroup_;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class GetAvailableReceipt implements Specification<TouristGroup> {
    private boolean question;

    public GetAvailableReceipt(boolean question) {
        this.question = question;
    }

    @Override
    public Predicate getPredicate(Root<TouristGroup> root, CriteriaBuilder builder) {
        return builder.equal(root.get(TouristGroup_.isAvailableReceipt), question);
    }
}
