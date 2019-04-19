package com.luobo.repository.impl;


import com.luobo.repository.BaseRepository;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * . Description: Date: 2019/4/18 16:33
 *
 * @author: ws
 * @version: 1.0
 */
public class BaseRepositoryImpl<T, ID extends Serializable> implements BaseRepository<T,ID> {
	@Autowired
	private SessionFactory sessionFactory;

	public Session getCurrentSession() {
		return this.sessionFactory.openSession();
	}

	private Class entityClass = null;
	protected Class<T> getEntityClass(){
		if(entityClass==null){
			Type genType = getClass().getGenericSuperclass();
			Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
			this.entityClass = (Class) params[0];
		}
		return this.entityClass;
	}

	@Override
	public T load(ID id) {
		return (T)getCurrentSession().load(getEntityClass(),id);
	}

	@Override
	public T get(ID id) {
		return (T)getCurrentSession().get(getEntityClass(),id);
	}

	@Override
	public List<T> findAll() {
		String hql = "from " + getEntityClass().getSimpleName();
		Query query = getCurrentSession().createQuery(hql);
		List<T> bigWorks = query.list();
		return bigWorks;
	}

	@Override
	public void persist(T entity) {
		getCurrentSession().persist(entity);
	}

	@Override
	public ID save(T entity) {
		return (ID)getCurrentSession().save(entity);
	}

	@Override
	public void update(T entity) throws Exception {

	}

	@Override
	public void delete(ID id) {
		String hql = "delete from " + getEntityClass().getSimpleName() + " b where b.id = :id";
		System.out.println(hql);
		getCurrentSession().createQuery(hql).setParameter("id",id).executeUpdate();
	}

	@Override
	public void flush() {
		getCurrentSession().flush();
	}
}
