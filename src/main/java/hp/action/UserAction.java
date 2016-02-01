package hp.action;

import hp.action.base.BaseAction;
import hp.model.SysUser;
import hp.model.pageModel.DataGrid;
import hp.service.UserService;
import hp.util.HqlHelper;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

@Namespace("/user")
@Action(value = "userAction")
public class UserAction extends BaseAction<SysUser> {

	@Autowired
	private UserService userService;

	public void test() {
		System.out.println("测试action1");
		userService.test();
	}

	public void save() {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			// TODO 判断登录名是否存在
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
		HqlHelper hqlHelper = new HqlHelper(SysUser.class, "u")//
				.addCondition(model.getLoginname() != null, "u.loginname like", "loginname", "%" + model.getLoginname() + "%")// 登录名模糊查询
				.addCondition(model.getName() != null, "u.name like", "name", "%" + model.getName() + "%")//姓名模糊查询
				.addCondition(model.getSex() != null, "u.sex =", "sex", model.getSex())//过滤性别
				.addOrder("u." + sort, order);
		List<SysUser> users = userService.find(hqlHelper.getQueryListHql(), hqlHelper.getMap(), page, rows);
		Long count = userService.count(hqlHelper.getQueryCountHql(), hqlHelper.getMap());
		// List<SysUser> users = userService.find("from SysUser u");
		grid.setRows(users);
		grid.setTotal(count);
		writeJson(grid);
	}

	public void update() {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			// TODO 判断登录名是否存在
			SysUser user = userService.get(model.getId());
			//如果有修改头像，则将原来头像删除
			String webParentPath = new File(getRequest().getSession().getServletContext().getRealPath("/")).getParent();// 当前WEB环境的上层目录
			if(null != user.getPhoto() && !"".equals(user.getPhoto())){
				String realPath = webParentPath + user.getPhoto();// 文件上传到服务器的真实路径
				deleteFile(realPath);
			}
			//修改数据库数据
			BeanUtils.copyProperties(model, user, new String[] { "createdatetime" });
			userService.update(user);
			map.put("msg", "编辑成功");
			map.put("success", true);
		} catch (Exception e) {
			map.put("msg", "编辑失败");
			map.put("success", false);
		}
		writeJson(map);
	}

	public void delete(){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			SysUser user = userService.get(model.getId());
			String webParentPath = new File(getRequest().getSession().getServletContext().getRealPath("/")).getParent();// 当前WEB环境的上层目录
			if(null != user.getPhoto() && !"".equals(user.getPhoto())){
				String realPath = webParentPath + user.getPhoto();// 文件上传到服务器的真实路径
				deleteFile(realPath);
			}
			userService.delete(user);
			map.put("msg", "删除成功");
			map.put("success", true);
		} catch (Exception e) {
			map.put("msg", "删除失败");
			map.put("success", false);
		}
		writeJson(map);
	}
	
	public void getById() {
		SysUser user = userService.get(model.getId());
		writeJson(user);
	}
	
	private void deleteFile(String realPath){
		File f = new File(realPath);
		if(f.exists()){
			f.delete();
		}
	}

}