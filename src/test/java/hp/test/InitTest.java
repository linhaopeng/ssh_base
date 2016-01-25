package hp.test;

import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import hp.model.SysResource;
import hp.model.SysResourcetype;
import hp.service.InitService;
import hp.service.SysResourcetypeService;
import hp.service.UserService;

public class InitTest {
	public static void main(String[] args) {
		
		
		SysResourcetype sysResourcetype = new SysResourcetype();
		sysResourcetype.setId(1);
		sysResourcetype.setName("菜单");
		SysResourcetype sysResourcetype2 = new SysResourcetype();
		sysResourcetype2.setId(2);
		sysResourcetype2.setName("功能");
		
		ApplicationContext ac = new ClassPathXmlApplicationContext(new String[] { "classpath:spring.xml", "classpath:spring-hibernate.xml" });
		SysResourcetypeService sysResourcetypeService = (SysResourcetypeService) ac.getBean("sysResourcetypeService");
		sysResourcetypeService.save(sysResourcetype);
		sysResourcetypeService.save(sysResourcetype2);
		
		SysResource sysResource = new SysResource();
		sysResource.setUrl("/welcome.jsp");
		sysResource.setDescription("系统管理描述");
		sysResource.setName("系统管理");
		sysResource.setCreatedatetime(new Date());
		
		SysResource sysResource2 = new SysResource();
		sysResource2.setUrl("/welcome.jsp");
		sysResource2.setDescription("系统报表描述");
		sysResource2.setName("系统报表");
		sysResource2.setCreatedatetime(new Date());
		xtgl(sysResource,sysResourcetype);
		xtbb(sysResource2,sysResourcetype);
		InitService userService = (InitService) ac.getBean("initService");
		sysResource.setResourcetype(sysResourcetype);
		sysResource2.setResourcetype(sysResourcetype);
		userService.save(sysResource);
		userService.save(sysResource2);
	}
	
	//资源管理
	public static void xtgl(SysResource sysResource, SysResourcetype sysResourcetype){
		SysResource zygl = new SysResource();
		zygl.setUrl("/base/syresource!treeGrid");
		zygl.setDescription("资源管理描述");
		zygl.setName("资源管理");
		zygl.setCreatedatetime(new Date());
		zygl.setResourcetype(sysResourcetype);
		
		SysResource jsgl = new SysResource();
		jsgl.setUrl("/base/syresource!treeGrid");
		jsgl.setDescription("角色管理描述");
		jsgl.setName("角色管理");
		jsgl.setCreatedatetime(new Date());
		jsgl.setResourcetype(sysResourcetype);
		
		SysResource yhgl = new SysResource();
		yhgl.setUrl("/base/syresource!treeGrid");
		yhgl.setDescription("用户管理描述");
		yhgl.setName("用户管理");
		yhgl.setCreatedatetime(new Date());
		yhgl.setResourcetype(sysResourcetype);
		
		zygl.setResource(sysResource);
		jsgl.setResource(sysResource);
		yhgl.setResource(sysResource);
		sysResource.getResources().add(zygl);
		sysResource.getResources().add(jsgl);
		sysResource.getResources().add(yhgl);
	}
	
	//资源管理
	public static void xtbb(SysResource sysResource, SysResourcetype sysResourcetype){
		SysResource zc = new SysResource();
		zc.setUrl("/base/syresource!treeGrid");
		zc.setDescription("注册时间分布描述");
		zc.setName("注册时间分布");
		zc.setCreatedatetime(new Date());
		zc.setResourcetype(sysResourcetype);
		
		
		SysResource yh = new SysResource();
		yh.setUrl("/base/syresource!treeGrid");
		yh.setDescription("用户角色分布描述");
		yh.setName("用户角色分布");
		yh.setCreatedatetime(new Date());
		yh.setResourcetype(sysResourcetype);
		
		
		zc.setResource(sysResource);
		yh.setResource(sysResource);
		sysResource.getResources().add(zc); 
		sysResource.getResources().add(yh); 
	}
}
