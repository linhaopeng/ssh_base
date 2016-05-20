package hp.service.impl;

import hp.model.SysResource;
import hp.service.SysResourceService;
import hp.util.HqlHelper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service("sysResourceService")
public class SysResourceServiceImpl extends BaseServiceImpl<SysResource> implements SysResourceService {

	public void updateResource(SysResource resource) {
		// TODO Auto-generated method stub

	}

	/**
	 * 为列表添加了缓存，查询一次过后，只要不重启服务，缓存一直存在，不需要再查询数据库了，节省了一些资源
	 * 
	 * 在ehcache.xml里面需要有对应的value
	 * 
	 * <cache name="resourcetypeServiceCache"
	 * 
	 * key是自己设定的一个ID，用来标识缓存
	 */
	@Cacheable(value = "resourcetypeServiceCache", key = "'urlsCache'")
	public List<String> getResources() {
		List<SysResource> res = find(new HqlHelper(SysResource.class).getQueryListHql());
		List<String> lists = new ArrayList<String>();
		for (SysResource sysResource : res) {
			lists.add(sysResource.getUrl());
		}
		return lists;
	}

}
