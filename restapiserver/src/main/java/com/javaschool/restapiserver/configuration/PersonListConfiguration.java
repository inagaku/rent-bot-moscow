package com.javaschool.restapiserver.configuration;

import com.javaschool.restapiserver.models.Person;
import com.javaschool.restapiserver.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class PersonListConfiguration {

    @Autowired
    PersonService personService;

    @Bean("personList")
    public List<Person> personList() {
        return personService.allPersons();
    }
}
