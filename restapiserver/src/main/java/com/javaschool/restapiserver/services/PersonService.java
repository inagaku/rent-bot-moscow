package com.javaschool.restapiserver.services;

import com.javaschool.restapiserver.models.Advertisement;
import com.javaschool.restapiserver.models.Person;
import com.javaschool.restapiserver.repository.CriterionRepository;
import com.javaschool.restapiserver.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private CriterionRepository criterionRepository;

    public void savePerson(Person person) {
        criterionRepository.save(person.getCrit());
        personRepository.save(person);
    }

    public void deletePerson(Person person) {
        personRepository.delete(person);
    }

    public void updatePerson(Person person) {
    }

    public List<Person> allPersons() {
        return personRepository.findAll();
    }

    public List<Person> allPersonsByCriteries(Advertisement advertisement) {
        return allPersons()
                .stream()
                .filter(person -> person.getCrit().getStartValue() <= advertisement.getPrice() && person.getCrit().getEndValue() >= advertisement.getPrice())
                .filter(person -> Arrays.stream(advertisement.getMetro().split(",")).anyMatch(metro -> person.getCrit().getSubway().contains(metro)))
                .collect(Collectors.toList());
    }

    public void getPerson(Person person) {
        personRepository.findById(person.getUserId());
    }
}
