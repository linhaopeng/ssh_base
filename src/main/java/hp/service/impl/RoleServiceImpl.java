package hp.service.impl;

import hp.dao.impl.BaseDaoImpl;
import hp.model.SysRole;
import hp.service.RoleService;

import org.springframework.stereotype.Service;

// 使用注解注入
@Service("roleService")
public class RoleServiceImpl extends BaseDaoImpl<SysRole> implements RoleService {

}