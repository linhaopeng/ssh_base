package hp.action;

import hp.action.base.BaseAction;
import hp.model.SysResource;
import hp.model.SysRole;
import hp.model.pageModel.DataGrid;
import hp.model.pageModel.ReturnJson;
import hp.util.HqlHelper;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.BeanUtils;

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

	/**
	 * 根据角色id获取角色已有权限
	 */
	public void getPrivilegeByRoleId() {
		String hql = "select distinct res from SysRole r join r.resources res where r.id=:id";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", model.getId());
		List<SysRole> resource = roleService.find(hql, map);
		writeJson(resource);
	}

	/**
	 * 修改角色权限
	 */
	public void grantPrivilege() {
		ReturnJson json = new ReturnJson("修改失败");
		try {
			SysRole role = roleService.get(model.getId());
			// 除去原有权限
			role.setResources(new HashSet<SysResource>());
			for (String pid : ids.split(",")) {
				if (!StringUtils.isBlank(pid)) {
					// 查询出前台已经勾选的权限
					int intpid = Integer.parseInt(pid);
					SysResource resource = sysResourceService.get(intpid);
					if (resource != null) {
						role.getResources().add(resource);
					}
				}
			}
			// 修改角色
			roleService.saveOrUpdate(role);
			json.setMsg("修改成功");
			json.setSuccess(true);
		} catch (Exception e) {
		}
		writeJson(json);
	}

	public void update() {
		ReturnJson json = new ReturnJson("删除失败");
		try {
			SysRole role = roleService.get(model.getId());
			BeanUtils.copyProperties(model, role, new String[] { "createdatetime" });
			roleService.update(role);
			json.setMsg("修改成功");
			json.setSuccess(true);
		} catch (Exception e) {
		}
		writeJson(json);
	}

}
