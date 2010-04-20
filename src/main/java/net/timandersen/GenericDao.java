package net.timandersen;

import org.hibernate.criterion.DetachedCriteria;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;


public interface GenericDao<T> {

    T findById(Long id);

    List<T> findAll();

    void save(T entity);

    void delete(T entity);

    List<T> findByCriteria(DetachedCriteria criteria);

    T load(Serializable id);

    void saveOrUpdateAll(Collection<T> entities);

    void deleteAll(Collection<T> entities);

}
