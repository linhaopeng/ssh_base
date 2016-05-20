package hp.service.impl;

import hp.model.SysRole;
import hp.service.RoleService;

import org.springframework.stereotype.Service;

// 使用注解注入
@Service("roleService")
public class RoleServiceImpl extends BaseServiceImpl<SysRole> implements RoleService {

	public void delete2(SysRole o) {
		SysRole sysRole = super.get(o.getId());
		super.delete(sysRole);
	}

}