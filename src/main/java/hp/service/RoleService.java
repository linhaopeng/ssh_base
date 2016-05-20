package hp.service;

import hp.dao.BaseDao;
import hp.model.SysRole;

public interface RoleService extends BaseDao<SysRole>{
	public void delete2(SysRole o);
}
