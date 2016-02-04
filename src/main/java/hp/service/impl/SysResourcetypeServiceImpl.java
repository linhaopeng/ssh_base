package hp.service.impl;

import hp.dao.impl.BaseDaoImpl;
import hp.model.SysResourcetype;
import hp.service.SysResourcetypeService;
import hp.util.HqlHelper;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service("sysResourcetypeService")
public class SysResourcetypeServiceImpl extends BaseDaoImpl<SysResourcetype> implements SysResourcetypeService {

	/**
	 * 为列表添加了缓存，查询一次过后，只要不重启服务，缓存一直存在，不需要再查询数据库了，节省了一些资源
	 * 
	 * 在ehcache.xml里面需要有对应的value
	 * 
	 * <cache name="resourcetypeServiceCache"
	 * 
	 * key是自己设定的一个ID，用来标识缓存
	 */
	@Cacheable(value = "resourcetypeServiceCache", key = "'resourcetypeServiceCache'")
	public List<SysResourcetype> findResourcetype() {
		return find(new HqlHelper(SysResourcetype.class).getQueryListHql());
	}
}
