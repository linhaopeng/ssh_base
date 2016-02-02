package hp.action;

import hp.action.base.BaseAction;
import hp.model.SysResource;
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
}
