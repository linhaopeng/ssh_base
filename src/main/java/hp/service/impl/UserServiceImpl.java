package hp.service.impl;

import hp.dao.impl.BaseDaoImpl;
import hp.model.SysUser;
import hp.service.UserService;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

// 使用注解注入
@Service("userService")
public class UserServiceImpl extends BaseDaoImpl<SysUser> implements UserService {
	public void test() {
		System.out.println("测试spring");
	}

	public SysUser login(SysUser user) {
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = "from SysUser u where u.loginname =:name and u.pwd=:pwd";
		params.put("name",user.getName());
		params.put("pwd",user.getPwd());
		return get(hql, params);
	}


}