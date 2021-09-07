package com.javaschool.restapiserver.controllers;

import com.javaschool.restapiserver.models.Person;
import com.javaschool.restapiserver.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class UserController {
    @Autowired
    private PersonService personService;

    @PostMapping("/save")
    public String saveUser(@RequestBody Person person) {
        personService.savePerson(person);
        return "saved";
    }
    
    @PostMapping("/delete")
    public String deleteUser(@RequestBody Person person) {
        personService.deletePerson(person);
        return "deleted";
    }
    
    @GetMapping("/update")
    public String updatePerson(@RequestBody Person person) {
        personService.updatePerson(person);
        return "updated";
    }

    @GetMapping("/all")
    public List<Person> allPersons() {
        return personService.allPersons();
    }
}
