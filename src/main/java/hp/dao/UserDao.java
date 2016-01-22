package hp.dao;

import hp.model.User;

import java.io.Serializable;

public interface UserDao {
	public Serializable save(User t);
}
