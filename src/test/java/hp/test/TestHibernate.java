package hp.test;

import hp.model.SysUser;
import hp.service.UserService;

import java.util.Date;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestHibernate {
	
	@Test
	public void testSys() {
		/*
		 * 在maven打包的时候，会先编译测试代码，所以先注释掉。
		 */
		ApplicationContext ac = new ClassPathXmlApplicationContext(new String[] { "classpath:spring.xml", "classpath:spring-hibernate.xml" });
		UserService userService = (UserService) ac.getBean("userService");
		SysUser t = new SysUser();
		t.setName("孙宇");
		t.setLoginname("孙宇");
		t.setPwd("123465");
		t.setAge(25);
		t.setSex("男");
		t.setCreatedatetime(new Date());
		userService.save(t);
	}
	
	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext(new String[] { "classpath:spring.xml", "classpath:spring-hibernate.xml" });
		UserService userService = (UserService) ac.getBean("userService");
		SysUser t = new SysUser();
		t.setName("孙宇");
		t.setLoginname("孙宇");
		t.setPwd("123465");
		t.setAge(25);
		t.setSex("男");
		t.setCreatedatetime(new Date());
		userService.save(t);
	}

}
