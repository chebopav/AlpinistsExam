package dao;

import dao.specifications.alpinist_specification.GetByAgeFromTo;
import entity.Alpinist;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Objects;

public class AlpinistDao implements Dao<Alpinist, Integer>{
    private EntityManager manager;

    public AlpinistDao(EntityManager manager) {
        this.manager = Objects.requireNonNull(manager);
    }

    @Override
    public void add(Alpinist alpinist) {
        manager.persist(alpinist);
    }

    @Override
    public void update(Alpinist alpinist) {
        manager.merge(alpinist);
    }

    @Override
    public void remove(Alpinist alpinist) {
        manager.remove(alpinist);
    }

    @Override
    public Alpinist getByPK(Integer id) {
        return manager.find(Alpinist.class, id);
    }

    @Override
    public void deleteByPK(Integer id) {
        Alpinist alpinist = getByPK(id);
        if (alpinist != null) remove(alpinist);
    }

    @Override
    public List<Alpinist> getAll() {
        CriteriaQuery<Alpinist> query = manager.getCriteriaBuilder().createQuery(Alpinist.class);
        Root<Alpinist> root = query.from(Alpinist.class);
        query.select(root);
        return manager.createQuery(query).getResultList();
    }

    public List<Alpinist> getAlpinistsByAgeFromTo(int from, int to){
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Alpinist> query = builder.createQuery(Alpinist.class);
        Root<Alpinist> root = query.from(Alpinist.class);
        Predicate condition = new GetByAgeFromTo(from, to).getPredicate(root, builder);
        query.where(condition);
        return manager.createQuery(query).getResultList();
    }
}
