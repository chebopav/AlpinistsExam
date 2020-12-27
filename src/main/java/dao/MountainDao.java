package dao;

import dao.specifications.mountain_specifications.GetByCountryName;
import dao.specifications.mountain_specifications.GetByName;
import entity.Mountain;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Objects;

public class MountainDao implements Dao<Mountain, Integer>{
    private EntityManager manager;

    public MountainDao(EntityManager manager) {
        this.manager = Objects.requireNonNull(manager);
    }

    @Override
    public void add(Mountain mountain) {
        manager.persist(mountain);
    }

    @Override
    public void update(Mountain mountain) {
        manager.merge(mountain);
    }

    @Override
    public void remove(Mountain mountain) {
        manager.remove(mountain);
    }

    @Override
    public Mountain getByPK(Integer i) {
        return manager.find(Mountain.class, i);
    }

    @Override
    public void deleteByPK(Integer i) {
        Mountain mountain = getByPK(i);
        if (mountain != null) remove(mountain);
    }

    @Override
    public List<Mountain> getAll() {
        CriteriaQuery<Mountain> query = manager.getCriteriaBuilder().createQuery(Mountain.class);
        Root<Mountain> root = query.from(Mountain.class);
        query.select(root);
        return manager.createQuery(query).getResultList();
    }

    public List<Mountain> getByCountryName(String countryName){
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Mountain> query = builder.createQuery(Mountain.class);
        Root<Mountain> root = query.from(Mountain.class);
        Predicate condition = new GetByCountryName(countryName).getPredicate(root, builder);
        query.where(condition);
        return manager.createQuery(query).getResultList();
    }

    public Mountain getByName(String name){
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Mountain> mountainQuery = builder.createQuery(Mountain.class);
        Root<Mountain> mountainRoot = mountainQuery.from(Mountain.class);
        Predicate mountainCondition = new GetByName(name).getPredicate(mountainRoot, builder);
        mountainQuery.where(mountainCondition);
        return manager.createQuery(mountainQuery).getSingleResult();
    }
}
