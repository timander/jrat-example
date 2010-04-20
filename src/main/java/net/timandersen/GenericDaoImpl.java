package net.timandersen;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;


//** Used example from https://www.hibernate.org/328.html
@SuppressWarnings({"unchecked"})
public class GenericDaoImpl<T> extends HibernateDaoSupport implements GenericDao<T> {

    private Class<T> persistentClass;

    public GenericDaoImpl() {
        persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    public T findById(Long id) {
        return (T) getHibernateTemplate().load(persistentClass, id);
    }

    @Override
    public List<T> findAll() {
        return getHibernateTemplate().loadAll(persistentClass);
    }

    @Override
    public List<T> findByCriteria(DetachedCriteria criteria) {
        return getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    public T load(Serializable id) {
        return (T) getHibernateTemplate().load(persistentClass, id);
    }

    @Override
    public void save(T entity) {
        getHibernateTemplate().saveOrUpdate(entity);
        getHibernateTemplate().flush();
    }

    @Override
    public void saveOrUpdateAll(Collection<T> entities) {
        getHibernateTemplate().saveOrUpdateAll(entities);
        getHibernateTemplate().flush();
    }

    @Override
    public void deleteAll(Collection<T> entities) {
        getHibernateTemplate().deleteAll(entities);
    }

    @Override
    public void delete(T entity) {
        getHibernateTemplate().delete(entity);
        getHibernateTemplate().flush();
    }

    protected T firstOrNull(List<T> list) {
        return list.size() == 0 ? null : list.get(0);
    }
}
