package hp.action;

import hp.action.base.BaseAction;
import hp.model.SysResourcetype;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;

@Namespace("/resourcetype")
@Action(value = "sysResourcetypeAction")
public class SysResourcetypeAction extends BaseAction<SysResourcetype> {
	public void getResourcetypeList(){
		List<SysResourcetype> findResourcetype = sysResourcetypeService.findResourcetype();
		writeJson(findResourcetype);
	}
}
