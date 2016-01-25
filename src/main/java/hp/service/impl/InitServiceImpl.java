package hp.service.impl;

import org.springframework.stereotype.Service;

import hp.dao.impl.BaseDaoImpl;
import hp.model.SysResource;
import hp.service.InitService;

@Service("initService")
public class InitServiceImpl extends BaseDaoImpl<SysResource> implements InitService{

}
