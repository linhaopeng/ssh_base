package hp.action;

import hp.action.base.BaseAction;
import hp.model.User;
import hp.service.UserService;

import java.util.Date;
import java.util.UUID;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

@Namespace("/user")
@Action(value = "userAction")
public class UserAction extends BaseAction<User>{

	@Autowired
	private UserService userService;

	public void test() {
		System.out.println("测试action1");
		userService.test();
	}

	public void save() {
		User t = new User();
		t.setId(1);
		t.setName("孙宇2");
		t.setPwd("123465");
		t.setCreatedatetime(new Date());
		userService.save(t);
	}

	public void get() {
		User user = userService.get(model.getId());
		System.out.println(user);
	}
}