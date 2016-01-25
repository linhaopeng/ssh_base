package hp.action;

import hp.action.base.BaseAction;
import hp.model.SysUser;
import hp.service.UserService;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

@Namespace("/user")
@Action(value = "testAction")
public class TestAction extends BaseAction<SysUser>{
	@Autowired
	private UserService userService;
	
	public void login(){
		SysUser user = userService.login(model);
		writeJson(user);
	}
}
