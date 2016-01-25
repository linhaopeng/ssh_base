package hp.service.impl;

import org.springframework.stereotype.Service;

import hp.dao.impl.BaseDaoImpl;
import hp.model.SysResourcetype;
import hp.service.SysResourcetypeService;

@Service("sysResourcetypeService")
public class SysResourcetypeServiceImpl extends BaseDaoImpl<SysResourcetype> implements SysResourcetypeService{

}
