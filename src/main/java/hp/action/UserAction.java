package hp.action;

import hp.action.base.BaseAction;
import hp.model.SysRole;
import hp.model.SysUser;
import hp.model.pageModel.DataGrid;
import hp.model.pageModel.ReturnJson;
import hp.util.HqlHelper;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.BeanUtils;

@Namespace("/user")
@Action(value = "userAction")
// 访问路径 /user/userAction!save.action
public class UserAction extends BaseAction<SysUser> {

	/**
	 * 保存用户
	 */
	public void save() {
		ReturnJson json = new ReturnJson("添加失败");
		try {
			// TODO 判断登录名是否存在
			userService.save(model);
			json.setMsg("添加成功");
			json.setSuccess(true);
		} catch (Exception e) {
			deletePhoto(model);
		}
		writeJson(json);
	}

	/**
	 * 获取用户列表 ，分页+模糊查询
	 */
	public void list() {
		DataGrid<SysUser> grid = userService.findByPage(model, sort, order, page, rows);
		writeJson(grid);
	}

	/**
	 * 更新用户
	 */
	public void update() {
		ReturnJson json = new ReturnJson("编辑失败");
		try {
			// TODO 判断登录名是否存在
			SysUser user = userService.get(model.getId());
			deletePhoto(user); // 删除头像
			// 修改数据库数据
			BeanUtils.copyProperties(model, user, new String[] { "createdatetime" });
			userService.update(user);
			json.setMsg("编辑成功");
			json.setSuccess(true);
		} catch (Exception e) {
		}
		writeJson(json);
	}

	/**
	 * 删除用户
	 */
	public void delete() {
		ReturnJson json = new ReturnJson("删除失败");
		try {
			SysUser user = userService.get(model.getId());
			deletePhoto(user);// 删除头像
			userService.delete(user);
			json.setMsg("删除成功");
			json.setSuccess(true);
		} catch (Exception e) {
		}
		writeJson(json);
	}

	/**
	 * 删除头像
	 * 
	 * @param user
	 */
	private void deletePhoto(SysUser user) {
		String webParentPath = new File(getRequest().getSession().getServletContext().getRealPath("/")).getParent();// 当前WEB环境的上层目录
		if (null != user.getPhoto() && !"".equals(user.getPhoto())) {
			String realPath = webParentPath + user.getPhoto();// 文件上传到服务器的真实路径
			File f = new File(realPath);
			if (f.exists()) {
				f.delete();
			}
		}
	}

	/**
	 * 获取单个用户
	 */
	public void getById() {
		SysUser user = userService.get(model.getId());
		writeJson(user);
	}

	/**
	 * 查找所有角色
	 */
	public void getAllRole() {
		List<SysRole> roles = roleService.find(new HqlHelper(SysRole.class, "r").getQueryListHql());
		writeJson(roles);
	}

	/**
	 * 根据用户id查找该用户角色
	 */
	public void getRoleByUserId() {
		String hql = "select distinct u.roles from SysUser u join u.roles where u.id = :id";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", model.getId());
		List<SysRole> roles = roleService.find(hql, params);
		writeJson(roles);
	}

	/**
	 * 修改用户权限
	 */
	public void grantRole() {
		ReturnJson json = new ReturnJson("修改失败");
		try {
			SysUser user = userService.get(model.getId());
			if (user != null) {
				// 清空之前的角色
				user.setRoles(new HashSet<SysRole>());
				// 循环添加角色
				for (String roleId : ids.split(",")) {
					if (!StringUtils.isBlank(roleId)) {
						int rid = Integer.parseInt(roleId);
						SysRole role = roleService.get(rid);
						if (role != null) {
							user.getRoles().add(role);
						}
					}
				}
			}
			// 修改角色
			userService.saveOrUpdate(user);
			json.setMsg("修改成功");
			json.setSuccess(true);
		} catch (Exception e) {
		}
		writeJson(json);
	}

}