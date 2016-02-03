package hp.action;

import hp.action.base.BaseAction;
import hp.model.SysResource;
import hp.model.pageModel.ReturnJson;
import hp.model.pageModel.Tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.BeanUtils;

@Namespace("/resource")
@Action(value = "sysResourceAction")
public class SysResourceAction extends BaseAction<SysResource> {

	public void getTree() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", 1);
		List<SysResource> resources = sysResourceService.find("from SysResource s where s.resourcetype.id=:id", params);
		List<Tree> tree = new ArrayList<Tree>();
		for (SysResource resource : resources) {
			Tree node = new Tree();
			// 复制属性值 这里只是复制了id跟pid
			BeanUtils.copyProperties(resource, node);

			node.setText(resource.getName());
			Map<String, String> attributes = new HashMap<String, String>();
			attributes.put("url", resource.getUrl());
			attributes.put("target", resource.getTarget());
			node.setAttributes(attributes);
			tree.add(node);
		}
		writeJson(tree);
	}
	
	/**
	 * 获取资源树
	 */
	public void getResourceTree() {
		List<SysResource> resources = sysResourceService.find("from SysResource s ");
		writeJson(resources);
	}
	
	/**
	 * 添加资源
	 */
	public void save(){
		ReturnJson json = new ReturnJson("添加失败");
		try {
			sysResourceService.save(model);
			json.setMsg("添加成功");
			json.setSuccess(true);
		} catch (Exception e) {
		}
		writeJson(json);
	}
	
	/**
	 * 根据id获取资源
	 */
	public void get(){
		SysResource sysResource = sysResourceService.get(model.getId());
		writeJson(sysResource);
	}
	
	/**
	 * 根据id获取资源
	 */
	public void update(){
		//SysResource sysResource = sysResourceService.get(model.getId());
		//writeJson(sysResource);
		ReturnJson json = new ReturnJson("修改失败");
		if (model.getId() != 0 ) {
			if (model.getResource() != null && model.getId() == model.getResource().getId()) {
				json.setMsg("父资源不可以是自己！");
			} else {
				updateResource(model);
				json.setMsg("修改成功");
				json.setSuccess(true);
			}
		}
		writeJson(json);
	}
	
	private void updateResource(SysResource resource) {
		if (resource.getId() != 0) {
			SysResource t = sysResourceService.get(resource.getId());
			BeanUtils.copyProperties(resource, t, new String[] { "createdatetime" });
			if (resource.getResource() != null) {// 说明前台选中上级节点。需要修改
				SysResource pt = sysResourceService.get(resource.getResource().getId());// 上级节点
				t.setResource(pt);
			} else {
				t.setResource(null);
			}
			sysResourceService.update(t);
		}
	}
	
}
