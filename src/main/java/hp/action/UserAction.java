package hp.action;

import java.util.Date;
import java.util.UUID;

import hp.action.base.BaseAction;
import hp.model.SysUser;
import hp.service.UserService;

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
		SysUser t = new SysUser();
		t.setName("孙宇2");
		t.setLoginname("浩鹏");
		t.setPwd("123465");
		t.setCreatedatetime(new Date());
		userService.save(t);
	}

	public void get() {
		SysUser user = userService.get(model.getId());
		System.out.println(user);
	}
}