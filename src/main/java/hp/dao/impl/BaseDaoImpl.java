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

	public Serializable save(T t) {
		return getSession().save(t);
	}

	public void delete(T t) {
		getSession().delete(t);
	}

	public void update(T t) {
		getSession().update(t);

	}

	public void saveOrUpdate(T t) {
		getSession().saveOrUpdate(t);
	}

	public T getById(Serializable id) {
		return (T) getSession().get(clazz, id);
	}

	public T getByHql(String hql) {
		Query q = getSession().createQuery(hql);
		return (T) q.uniqueResult();
	}

	public T getByHql(String hql, Map<String, Object> params) {
		Query q = getSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return (T) q.uniqueResult();
	}

	public List<T> find(String hql) {
		return getSession().createQuery(hql).list();
	}

	public List<T> find(String hql, Map<String, Object> params) {
		Query q = getSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.list();
	}

	public List<T> find(String hql, int page, int rows) {
		return getSession().createQuery(hql)//
				.setFirstResult((page - 1) * rows)//
				.setMaxResults(rows).list();
	}

	public List<T> find(String hql, Map<String, Object> params, int page, int rows) {
		Query q = getSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.setFirstResult((page - 1) * rows)//
				.setMaxResults(rows).list();
	}

	public Long count(String hql) {
		Query q = getSession().createQuery(hql);
		return (Long) q.uniqueResult();
	}

	public Long count(String hql, Map<String, Object> params) {
		Query q = getSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return (Long) q.uniqueResult();
	}

	public int executeHql(String hql) {
		Query q = getSession().createQuery(hql);
		return q.executeUpdate();
	}

	public int executeHql(String hql, Map<String, Object> params) {
		Query q = getSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.executeUpdate();
	}

	public List<Map> findBySql(String sql) {
		SQLQuery q = getSession().createSQLQuery(sql);
		return q.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
	}

	public List<Map> findBySql(String sql, int page, int rows) {
		SQLQuery q = getSession().createSQLQuery(sql);
		return q.setFirstResult((page - 1) * rows)//
				.setMaxResults(rows)//
				.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
	}

	public List<Map> findBySql(String sql, Map<String, Object> params) {
		SQLQuery q = getSession().createSQLQuery(sql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
	}

	public List<Map> findBySql(String sql, Map<String, Object> params, int page, int rows) {
		SQLQuery q = getSession().createSQLQuery(sql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.setFirstResult((page - 1) * rows).setMaxResults(rows).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
	}

	public int executeSql(String sql) {
		SQLQuery q = getSession().createSQLQuery(sql);
		return q.executeUpdate();
	}

	public int executeSql(String sql, Map<String, Object> params) {
		SQLQuery q = getSession().createSQLQuery(sql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.executeUpdate();
	}

	public BigInteger countBySql(String sql) {
		SQLQuery q = getSession().createSQLQuery(sql);
		return (BigInteger) q.uniqueResult();
	}

	public BigInteger countBySql(String sql, Map<String, Object> params) {
		SQLQuery q = getSession().createSQLQuery(sql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return (BigInteger) q.uniqueResult();
	}

}
