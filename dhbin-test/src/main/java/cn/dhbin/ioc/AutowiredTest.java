package cn.dhbin.ioc;

import cn.dhbin.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Arrays;

/**
 * @author DHB
 */
@Configuration
@ComponentScan({"cn.dhbin.domain", "cn.dhbin.service", "cn.dhbin.ioc"})
public class AutowiredTest {

	private final PersonService[] personServices;
	private final PersonService personService;

	/**
	 * Spring团队建议：
	 * Always use constructor based dependency injection in your beans.
	 * Always use assertions for mandatory dependencies
	 * 大概意思是建议使用构造器注入bean，并且始终使用断言来强制依赖
	 * <p>
	 * AutoWired首先是根据byType规则寻找符合条件的bean，如果存在多个再通过ByName
	 *
	 * @param personServices personServices
	 * @param personService  personService
	 */
	@Autowired
	public AutowiredTest(PersonService[] personServices, PersonService personService) {
		this.personService = personService;
		this.personServices = personServices;
	}


	@PostConstruct
	public void init() {
		Arrays.stream(personServices)
				.map(personService -> personService.getPerson().getName())
				.forEach(System.out::println);

		/*
		 * @Primary > @Priority
		 * */
		System.out.println("指定优先级： " + personService.getPerson().getName());
	}


	public static void main(String[] args) {
		new AnnotationConfigApplicationContext(AutowiredTest.class);
	}
}
