package hp.dao.impl;

import hp.dao.UserDao;
import hp.model.User;

import java.io.Serializable;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {
	@Autowired
	private SessionFactory sessionFactory;

	public Serializable save(User t) {
		return this.sessionFactory.getCurrentSession().save(t);
	}
}
