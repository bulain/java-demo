package com.bulain.jencks.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bulain.common.dataset.DataSet;
import com.bulain.common.page.Page;
import com.bulain.common.test.ServiceTestCase;
import com.bulain.jencks.model.Person;
import com.bulain.jencks.pojo.PersonSearch;

@DataSet(file = "test-data/init_persons.xml")
public class PersonServiceImplTest extends ServiceTestCase {
	@Autowired
	private PersonService personService;

	@Test
	public void testFind() {
		PersonSearch search = new PersonSearch();
		search.setFirstName("first_name_page");
		search.setLastName("last_name_page");
		List<Person> find = personService.find(search);
		assertEquals(3, find.size());
	}

	@Test
	public void testCount() {
		PersonSearch search = new PersonSearch();
		search.setFirstName("first_name_page");
		search.setLastName("last_name_page");
		Long count = personService.count(search);
		assertEquals(Long.valueOf(3), count);
	}

	@Test
	public void testPage() {
		PersonSearch search = new PersonSearch();
		search.setFirstName("first_name_page");
		search.setLastName("last_name_page");
		Page page = new Page();
		List<Person> page2 = personService.page(search, page);
		assertEquals(3, page2.size());
	}

	@Test
	public void testGet() {
		Person person = personService.get(Long.valueOf(102));
		assertNotNull(person);
		assertEquals("first_name_102", person.getFirstName());
		assertEquals("last_name_102", person.getLastName());
	}

	@Test
	public void testInsert() {
		Person record = new Person();
		record.setFirstName("firstName");
		record.setLastName("lastName");
		personService.insert(record);
	}

	@Test
	public void testUpdate() {
		Person record = new Person();
		record.setId(Long.valueOf(103));
		record.setFirstName("firstName-updated");
		record.setLastName("lastName-updated");
		personService.update(record, true);
	}

	@Test
	public void testDelete() {
		personService.delete(Long.valueOf(101));
	}

}
