package com.bulain.common.service;

import com.bulain.common.dao.PagedMapper;
import com.bulain.common.service.PagedServiceImpl;
import com.bulain.common.dao.PersonMapper;
import com.bulain.common.model.Person;
import com.bulain.common.pojo.PersonSearch;

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
