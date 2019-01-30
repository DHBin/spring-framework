package cn.dhbin.ioc;

import cn.dhbin.serivce.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @author DHB
 */
@Configuration
@ComponentScan(basePackages = "cn.dhbin")
public class AutowiredTest {

	@Autowired
	private PersonService studentPersonServiceImpl;


	@PostConstruct
	public void init() {
		System.out.println(studentPersonServiceImpl.getPerson().getName());
	}


	public static void main(String[] args) {
		new AnnotationConfigApplicationContext(AutowiredTest.class);
	}
}
