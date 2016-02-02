package hp.service;

import hp.dao.BaseDao;
import hp.model.SysUser;
import hp.model.pageModel.DataGrid;

public interface UserService extends BaseDao<SysUser> {
	public void test();

	public SysUser login(SysUser user);

	public DataGrid<SysUser> findByPage(SysUser user, String sort, String order, Integer page, Integer rows);
}