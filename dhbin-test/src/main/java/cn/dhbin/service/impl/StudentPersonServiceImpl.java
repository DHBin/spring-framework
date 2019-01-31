package cn.dhbin.service.impl;

import cn.dhbin.domain.Person;
import cn.dhbin.service.PersonService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @author DHB
 */
@Service
@Primary
public class StudentPersonServiceImpl implements PersonService {

	@Override
	public Person getPerson() {
		Person person = new Person();
		person.setName("学生");
		return person;
	}


	@PostConstruct
	public void init() {
		System.out.println(this.getClass().getName() + " running");
	}
}
