package com.luobo.test;

import com.luobo.entity.Person;
import com.luobo.entity.Student;
import com.luobo.repository.PersonRepository;
import com.luobo.util.ClassLocationUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

/**
 * . Description: Date: 2019/3/8 16:32
 *
 * @author: ws
 * @version: 1.0
 */
@Component
public class Test1 {

	@Autowired
	private PersonRepository personRepository;

    @Test
	public void test() {
	    ClassLocationUtils.where(org.springframework.web.filter.CharacterEncodingFilter.class);
    }

	@Test
	public void test1() {
		Resource rs = new ClassPathResource("applicationContext.xml");
		BeanFactory ac = new XmlBeanFactory(rs);
		SessionFactory sessionFactory = (SessionFactory) ac.getBean("sessionFactory");
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		Student c =new Student();
		c.setName("王琛昱");
		session.save(c);
		t.commit();
		session.close();
		sessionFactory.close();
	}

}
