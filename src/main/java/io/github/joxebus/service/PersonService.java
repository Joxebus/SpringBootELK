package io.github.joxebus.service;

import io.github.joxebus.entity.Person;
import io.github.joxebus.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

import jakarta.validation.Valid;

@Service
@Validated
public class PersonService {

    private static final Logger logger = LoggerFactory.getLogger(PersonService.class);

    private PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person create(@Valid Person newPerson) {
        Person person = personRepository.save(newPerson);
        logger.debug("Person created: {}", person);
        return person;
    }

    public List<Person> findAll() {
        logger.debug("Looking for all people registered");
        return personRepository.findAll();
    }

    public Person update(@Valid Person newPerson) {
        if(newPerson.getId() < 1){
            throw new IllegalArgumentException("Can't update person with id ="+newPerson.getId());
        }
        Optional<Person> record = personRepository.findById(newPerson.getId());
        Person person = null;
        if(record.isPresent()){
            person = record.get();
            person.setName(newPerson.getName());
            person.setLastName(newPerson.getLastName());
            person.setAge(newPerson.getAge());
            person.setPhone(newPerson.getPhone());
            person = personRepository.save(person);
            logger.debug("Person updated: {}", person);
        }

        return person;
    }

    public void delete(int id) {
        Optional<Person> person = personRepository.findById(id);
        if(!person.isPresent()){
            throw new IllegalArgumentException("Can't delete person with id ="+id);
        }
        logger.debug("Deleting person: {}", person);
        personRepository.deleteById(id);
    }

    public Person findById(int id) {
        Optional<Person> person = personRepository.findById(id);
        if(!person.isPresent()){
            throw new IllegalArgumentException("There is no person with id ="+id);
        }
        logger.debug("Person found: {}", person);
        return person.get();
    }


}
