package com.luobo.controller;

import com.luobo.entity.Person;
import com.luobo.service.PersonService;
import com.luobo.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Created by ws
 * On 2/1/2017.12:36 AM
 */
@Controller
public class PersonController {

	@Autowired
	private PersonService personService;

	@RequestMapping(value = "/savePerson", method = RequestMethod.GET)
	@ResponseBody
	public String savePerson(){
		personService.savePerson();
		return "success!";
	}
}