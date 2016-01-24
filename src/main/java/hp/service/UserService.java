package hp.service;

import hp.dao.BaseDao;
import hp.model.User;

public interface UserService extends BaseDao<User>{
	public void test();
	public User login(User user);
}