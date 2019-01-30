package cn.dhbin.serivce.impl;

import cn.dhbin.domain.Person;
import cn.dhbin.serivce.PersonService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @author DHB
 */
@Service
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
