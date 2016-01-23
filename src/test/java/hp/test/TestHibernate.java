package hp.test;

import java.util.Date;
import java.util.UUID;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import hp.model.User;
import hp.service.UserService;

public class TestHibernate {

	@Test
	public void test() {
		/*
		 * 在maven打包的时候，会先编译测试代码，所以先注释掉。
		 */
		ApplicationContext ac = new ClassPathXmlApplicationContext(new String[] { "classpath:spring.xml", "classpath:spring-hibernate.xml" });
		UserService userService = (UserService) ac.getBean("userService");
		User t = new User();
		t.setName("孙宇");
		t.setPwd("123465");
		t.setCreatedatetime(new Date());
		userService.save(t);
	}

}
