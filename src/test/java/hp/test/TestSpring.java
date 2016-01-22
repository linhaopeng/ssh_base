package hp.test;


import hp.service.UserService;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpring {
	@Test
	public void test(){
		ApplicationContext ac = new ClassPathXmlApplicationContext(new String[]{"spring.xml"});
		UserService userService = (UserService) ac.getBean("userService");
		userService.test();
	}
}
