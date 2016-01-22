package hp.service.impl;

import hp.dao.UserDao;
import hp.model.User;
import hp.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
// 使用注解注入
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;
	public void test() {
		System.out.println("测试spring");
	}

	public void save(User t) {
		userDao.save(t);
	}

}