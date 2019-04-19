package com.luobo.service.impl;

import com.luobo.entity.Person;
import com.luobo.repository.PersonRepository;
import com.luobo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ws
 * On 2/2/2017.2:40 PM
 */
@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonRepository personRepository;

	public Long savePerson() {
		Person person = new Person();
		person.setUsername("XRog");
		person.setPhone("18381005946");
		person.setAddress("chenDu");
		person.setRemark("this is XRog");
		return personRepository.save(person);
	}
}
