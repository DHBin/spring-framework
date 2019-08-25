package cn.dhbin.aop.aopcontext;

import cn.dhbin.aop.aopcontext.service.IUserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 关键的是exposeProxy参数，当设置为true时，会把当前的代理对象放在ThreadLocal中，
 * 通过AopContext.currentProxy()取得当前代理对象
 *
 * @author donghaibin
 * @date 2019/8/25
 */
@Configuration
@ComponentScan(basePackages = "cn.dhbin.aop.aopcontext")
@EnableAspectJAutoProxy(exposeProxy = true)
public class AopContextTest {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AopContextTest.class);
		IUserService userService = context.getBean(IUserService.class);
		userService.login();
		userService.getUser();
		System.out.println("----------------");
		userService.combination();
	}
}
