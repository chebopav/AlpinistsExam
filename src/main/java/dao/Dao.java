package dao;

import java.util.List;

public interface Dao<T, PK> {
    void add(T t);
    void update(T t);
    void remove(T t);
    T getByPK(PK pk);
    void deleteByPK(PK pk);
    List<T> getAll();
}
