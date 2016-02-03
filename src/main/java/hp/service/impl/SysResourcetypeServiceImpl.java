package hp.service.impl;

import hp.dao.impl.BaseDaoImpl;
import hp.model.SysResourcetype;
import hp.service.SysResourcetypeService;
import hp.util.HqlHelper;

import java.util.List;

import org.springframework.stereotype.Service;

@Service("sysResourcetypeService")
public class SysResourcetypeServiceImpl extends BaseDaoImpl<SysResourcetype> implements SysResourcetypeService{

	public List<SysResourcetype> findResourcetype() {
		return find(new HqlHelper(SysResourcetype.class).getQueryListHql());
	}
}
