package dao;

import dao.specifications.groups_specifications.GetAvailableReceipt;
import dao.specifications.groups_specifications.GetByMountain;
import entity.Mountain;
import entity.TouristGroup;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.List;
import java.util.Objects;

public class TouristGroupDao implements Dao<TouristGroup, Integer>{
    private EntityManager manager;

    public TouristGroupDao(EntityManager manager) {
        this.manager = Objects.requireNonNull(manager);
    }

    @Override
    public void add(TouristGroup touristGroup) {
        manager.persist(touristGroup);
    }

    @Override
    public void update(TouristGroup touristGroup) {
        manager.merge(touristGroup);
    }

    @Override
    public void remove(TouristGroup touristGroup) {
        manager.remove(touristGroup);
    }

    @Override
    public TouristGroup getByPK(Integer id) {
        return manager.find(TouristGroup.class, id);
    }

    @Override
    public void deleteByPK(Integer id) {
        TouristGroup group = getByPK(id);
        if (group != null) remove(group);
    }

    @Override
    public List<TouristGroup> getAll() {
        CriteriaQuery<TouristGroup> query = manager.getCriteriaBuilder().createQuery(TouristGroup.class);
        Root<TouristGroup> root = query.from(TouristGroup.class);
        query.select(root);
        return manager.createQuery(query).getResultList();
    }

    public List<TouristGroup> getGroupsByMountainName(String mountainName){
        Mountain mountain = new MountainDao(manager).getByName(mountainName);
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<TouristGroup> groupQuery = builder.createQuery(TouristGroup.class);
        Root<TouristGroup> groupRoot = groupQuery.from(TouristGroup.class);
        Predicate groupCondition = new GetByMountain(mountain).getPredicate(groupRoot, builder);
        groupQuery.where(groupCondition);
        return manager.createQuery(groupQuery).getResultList();
    }

    public List<TouristGroup> getGroupsAvailable(){
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<TouristGroup> query = builder.createQuery(TouristGroup.class);
        Root<TouristGroup> root = query.from(TouristGroup.class);
        Predicate condition = new GetAvailableReceipt(true).getPredicate(root, builder);
        query.where(condition);
        return manager.createQuery(query).getResultList();
    }
}
