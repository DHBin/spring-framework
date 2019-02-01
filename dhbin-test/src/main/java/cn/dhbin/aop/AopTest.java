package cn.dhbin.aop;

import cn.dhbin.domain.Person;
import cn.dhbin.service.PersonService;
import cn.dhbin.service.SingService;
import cn.dhbin.service.impl.StudentPersonServiceImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.Arrays;

/**
 * Aspect是在ioc的容器中，以单例存在。
 *
 * @author DHB
 */
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
@ComponentScan(basePackages = {"cn.dhbin.domain", "cn.dhbin.service", "cn.dhbin.aop"})
public class AopTest {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AopTest.class);
		System.out.println("======打印所有BeanName=====");
		Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
		System.out.println("======打印所有BeanName=====");
		PersonService personService = context.getBean(StudentPersonServiceImpl.class);

		System.out.println("======打印AOP=====");
		Person person = personService.getPerson();
		System.out.println("======打印AOP=====");

		System.out.println("======打印名称=====");
		System.out.println(person.getName());
		System.out.println("======打印名称=====");

		System.out.println("======打印测试@DeclareParents=====");
		((SingService) personService).sing();
		System.out.println("======打印测试@DeclareParents=====");

	}

}
