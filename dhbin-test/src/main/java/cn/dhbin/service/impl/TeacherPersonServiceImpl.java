package cn.dhbin.service.impl;

import cn.dhbin.domain.Person;
import cn.dhbin.service.PersonService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Priority;

/**
 * @author DHB
 */
@Service
@Priority(2)
public class TeacherPersonServiceImpl implements PersonService {


	@Override
	public Person getPerson() {
		Person person = new Person();
		person.setName("教师");
		return person;
	}


	@PostConstruct
	public void init() {
		System.out.println(this.getClass().getName() + " running");
	}

}
