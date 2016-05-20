package hp.service;

import java.util.List;

import hp.dao.BaseDao;
import hp.model.SysResource;

public interface SysResourceService extends BaseDao<SysResource>{

	public void updateResource(SysResource resource);
	
	public List<String> getResources();

}
