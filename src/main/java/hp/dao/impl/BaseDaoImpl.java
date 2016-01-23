package hp.dao.impl;

import hp.dao.BaseDao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;

// @Transactional注解可以被继承，即对子类也有效
@SuppressWarnings("unchecked")
public abstract class BaseDaoImpl<T> implements BaseDao<T> {

	private Class<?> clazz = null; // clazz中存储了子类当前操作实体类型

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;
	
	public BaseDaoImpl(){
		// 如果子类调用当前构造方法,this代表的是子类对象
		System.out.println(this);
		System.out.println("获取父类信息:" + this.getClass().getSuperclass());
		System.out.println("获取父类信息包括泛型信息:" + this.getClass().getGenericSuperclass());
		ParameterizedType type=(ParameterizedType)this.getClass().getGenericSuperclass();
		clazz=(Class<?>)type.getActualTypeArguments()[0];
		System.out.println("clazz:" + clazz);
	}
	
	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	public Serializable save(T o) {
		return this.getSession().save(o);
	}


	public T get(int id) {
		return (T) this.getSession().get(clazz, id);
	}
	
	public T get(String hql) {
		Query q = this.getSession().createQuery(hql);
		List<T> l = q.list();
		if (l != null && l.size() > 0) {
			return l.get(0);
		}
		return null;
	}

	
	public T get(String hql, Map<String, Object> params) {
		Query q = this.getSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		List<T> l = q.list();
		if (l != null && l.size() > 0) {
			return l.get(0);
		}
		return null;
	}

	
	public void delete(T o) {
		this.getSession().delete(o);
	}

	
	public void update(T o) {
		this.getSession().update(o);
	}

	
	public void saveOrUpdate(T o) {
		this.getSession().saveOrUpdate(o);
	}

	
	public List<T> find(String hql) {
		Query q = this.getSession().createQuery(hql);
		return q.list();
	}

	
	public List<T> find(String hql, Map<String, Object> params) {
		Query q = this.getSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.list();
	}

	
	public List<T> find(String hql, Map<String, Object> params, int page, int rows) {
		Query q = this.getSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
	}

	
	public List<T> find(String hql, int page, int rows) {
		Query q = this.getSession().createQuery(hql);
		return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
	}

	
	public Long count(String hql) {
		Query q = this.getSession().createQuery(hql);
		return (Long) q.uniqueResult();
	}

	public Long count(String hql, Map<String, Object> params) {
		Query q = this.getSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return (Long) q.uniqueResult();
	}
}
