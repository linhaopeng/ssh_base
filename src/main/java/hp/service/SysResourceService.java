package hp.service;

import hp.dao.BaseDao;
import hp.model.SysResource;

public interface SysResourceService extends BaseDao<SysResource>{

	public void updateResource(SysResource resource);

}
