package hp.action;

import hp.action.base.BaseAction;
import hp.model.SysRole;
import hp.model.pageModel.DataGrid;
import hp.model.pageModel.ReturnJson;
import hp.util.HqlHelper;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;

@Namespace("/role")
@Action(value = "roleAction")
// 访问路径 /role/roleAction!方法.action
public class RoleAction extends BaseAction<SysRole> {
	public void list() {
		HqlHelper hqlHelper = new HqlHelper(SysRole.class, "r")//
				.addCondition(model.getName() != null, "r.name like", "name", "%" + model.getName() + "%")//
				.addOrder(sort, order);
		List<SysRole> roles = roleService.find(hqlHelper.getQueryListHql(), hqlHelper.getMap(), page, rows);
		Long count = roleService.count(hqlHelper.getQueryCountHql(), hqlHelper.getMap());
		DataGrid<SysRole> grid = new DataGrid<SysRole>();
		grid.setRows(roles);
		grid.setTotal(count);
		writeJson(grid);
	}

	public void save() {
		ReturnJson json = new ReturnJson("添加失败");
		try {
			roleService.save(model);
			json.setMsg("添加成功");
			json.setSuccess(true);
		} catch (Exception e) {
		}
		writeJson(json);
	}

	public void getById() {
		SysRole sysRole = roleService.get(model.getId());
		writeJson(sysRole);
	}

	public void delete() {
		ReturnJson json = new ReturnJson("删除失败");
		try {
			SysRole sysRole = roleService.get(model.getId());
			roleService.delete(sysRole);
			json.setMsg("删除成功");
			json.setSuccess(true);
		} catch (Exception e) {
		}
		writeJson(json);
	}
}
