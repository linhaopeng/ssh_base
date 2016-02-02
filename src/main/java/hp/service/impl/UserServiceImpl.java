package hp.service.impl;

import hp.dao.impl.BaseDaoImpl;
import hp.model.SysUser;
import hp.model.pageModel.DataGrid;
import hp.service.UserService;
import hp.util.HqlHelper;

import java.util.HashMap;
import java.util.List;
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

	public DataGrid<SysUser> findByPage(SysUser user, String sort, String order, Integer page, Integer rows) {
		DataGrid<SysUser> grid = new DataGrid<SysUser>();
		HqlHelper hqlHelper = new HqlHelper(SysUser.class, "u")//
				.addCondition(user.getLoginname() != null, "u.loginname like", "loginname", "%" + user.getLoginname() + "%")// 登录名模糊查询
				.addCondition(user.getName() != null, "u.name like", "name", "%" + user.getName() + "%")// 姓名模糊查询
				.addCondition(user.getSex() != null, "u.sex =", "sex", user.getSex())// 过滤性别
				.addOrder("u." + sort, order);
		List<SysUser> users = find(hqlHelper.getQueryListHql(), hqlHelper.getMap(), page, rows);
		Long count = count(hqlHelper.getQueryCountHql(), hqlHelper.getMap());
		// List<SysUser> users = userService.find("from SysUser u");
		grid.setRows(users);
		grid.setTotal(count);
		return grid;
	}


}