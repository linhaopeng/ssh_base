package hp.action;

import java.util.HashMap;
import java.util.Map;

import hp.action.base.BaseAction;
import hp.model.User;
import hp.service.UserService;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

@Namespace("/user")
@Action(value = "testAction")
public class TestAction extends BaseAction<User>{
	@Autowired
	private UserService userService;
	
	public void login(){
		User user = userService.login(model);
		writeJson(user);
	}
}
