package hp.service.impl;

import java.util.HashMap;
import java.util.Map;

import hp.dao.impl.BaseDaoImpl;
import hp.model.User;
import hp.service.UserService;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// 使用注解注入
@Service("userService")
public class UserServiceImpl extends BaseDaoImpl<User> implements UserService {
	public void test() {
		System.out.println("测试spring");
	}


	public User login(User user) {
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = "from User u where u.name =:name and u.pwd=:pwd";
		params.put("name",user.getName());
		params.put("pwd",user.getPwd());
		return get(hql, params);
	}

}