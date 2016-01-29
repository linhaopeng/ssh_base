package hp.action;

import hp.action.base.BaseAction;
import hp.model.SysUser;
import hp.model.pageModel.DataGrid;
import hp.service.UserService;
import hp.util.HqlHelper;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

@Namespace("/user")
@Action(value = "userAction")
public class UserAction extends BaseAction<SysUser>{

	@Autowired
	private UserService userService;

	public void test() {
		System.out.println("测试action1");
		userService.test();
	}

	public void save() {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			//TODO 判断登录名是否存在
			userService.save(model);
			map.put("msg", "添加成功");
			map.put("success", true);
		} catch (Exception e) {
			map.put("msg", "添加失败");
			map.put("success", false);
		}
		writeJson(map);
	}
	
	public void list() {
		DataGrid<SysUser> grid = new DataGrid<SysUser>();
		HqlHelper hqlHelper = new HqlHelper(SysUser.class,"u")//
			.addCondition(model.getLoginname()!=null, "u.loginname like","loginname", "%"+model.getLoginname()+"%")//添加登录名模糊查询
			.addOrder("u."+sort, order);
		List<SysUser> users = userService.find(hqlHelper.getQueryListHql(),hqlHelper.getMap(),page,rows);
		Long count = userService.count(hqlHelper.getQueryCountHql(),hqlHelper.getMap());
		//List<SysUser> users = userService.find("from SysUser u");
		grid.setRows(users);
		grid.setTotal(count);
		writeJson(grid);
	}

	public void getById() {
		SysUser user = userService.get(model.getId());
		writeJson(user);
	}

}