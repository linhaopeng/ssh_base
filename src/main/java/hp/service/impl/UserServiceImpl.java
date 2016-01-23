package hp.service.impl;

import hp.dao.impl.BaseDaoImpl;
import hp.model.User;
import hp.service.UserService;

import org.springframework.stereotype.Service;

@Service("userService")
// 使用注解注入
public class UserServiceImpl extends BaseDaoImpl<User> implements UserService {
	public void test() {
		System.out.println("测试spring");
	}

}