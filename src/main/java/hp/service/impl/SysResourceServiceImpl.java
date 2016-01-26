package hp.service.impl;

import hp.dao.impl.BaseDaoImpl;
import hp.model.SysResource;
import hp.service.SysResourceService;

import org.springframework.stereotype.Service;

@Service("sysResourceService")
public class SysResourceServiceImpl extends BaseDaoImpl<SysResource> implements SysResourceService {

}
