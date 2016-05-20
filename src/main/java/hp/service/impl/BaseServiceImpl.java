package hp.service.impl;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import hp.dao.impl.BaseDaoImpl;

@Service("baseService")
@Lazy(true)
public class BaseServiceImpl<T> extends BaseDaoImpl<T>{

}
