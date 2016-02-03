package hp.service;

import hp.dao.BaseDao;
import hp.model.SysResourcetype;

import java.util.List;

public interface SysResourcetypeService extends BaseDao<SysResourcetype>{
	public List<SysResourcetype> findResourcetype() ;
}
