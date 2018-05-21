package com.bulain.jencks.service;

import com.bulain.common.dao.PagedMapper;
import com.bulain.common.service.PagedServiceImpl;
import com.bulain.jencks.dao.PersonMapper;
import com.bulain.jencks.model.Person;
import com.bulain.jencks.pojo.PersonSearch;

public class PersonServiceImpl extends PagedServiceImpl<Person, PersonSearch> implements PersonService {
	private PersonMapper personMapper;

	@Override
	protected PagedMapper<Person, PersonSearch> getPagedMapper() {
		return personMapper;
	}

	public void setPersonMapper(PersonMapper personMapper) {
		this.personMapper = personMapper;
	}

}
