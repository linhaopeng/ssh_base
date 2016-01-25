package hp.service;

import hp.dao.BaseDao;
import hp.model.SysUser;

public interface UserService extends BaseDao<SysUser>{
	public void test();
	public SysUser login(SysUser user);
}