package io.github.joxebus.controller;

import io.github.joxebus.entity.Person;
import io.github.joxebus.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/people")
public class PersonController {

    private static final Logger logger = LoggerFactory.getLogger(PersonController.class);

    private PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Person create(@RequestBody Person newPerson){
        Person person = personService.create(newPerson);
        return person;
    }

    @PutMapping("/")
    public Person update(@RequestBody Person newPerson){
        Person person = personService.update(newPerson);
        return person;
    }

    @GetMapping(value = {"/", ""})
    public List<Person> list(){
        return personService.findAll();
    }

    @GetMapping(value = "/{id}")
    public Person getById(@PathVariable(name = "id") int id){
        return personService.findById(id);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable(name = "id") int id){
        personService.delete(id);
    }

    @ExceptionHandler
    void handleIllegalArgumentException(Exception e, HttpServletResponse response)
            throws IOException {
        logger.error("An error occurred: {}", e.getMessage(), e);
        response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.addHeader("message", e.getMessage());
    }
}
