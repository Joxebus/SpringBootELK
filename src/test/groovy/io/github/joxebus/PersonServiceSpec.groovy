package io.github.joxebus

import io.github.joxebus.repository.PersonRepository
import io.github.joxebus.entity.Person
import io.github.joxebus.service.PersonService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

import javax.validation.ConstraintViolationException

@SpringBootTest
class PersonServiceSpec extends Specification {

    @Autowired
    PersonService personService

    @Shared
    Person person

    def setup(){
        person = new Person()
        person.with {
            name = "Test"
            lastName = "Service"
            age = 20
        }
    }

    def "Person Service call repository save when entity has valid values"(){
        given:
        PersonRepository personRepository = Mock()
        personService = new PersonService(
                personRepository: personRepository
        )
        when:
        personService.create(person)

        then:
        1 * personRepository.save(person)
    }

    @Unroll("Testing invalid values name=#newName, lastName=#newLastName, age=#newAge, phone=#newPhone")
    def "Person with invalid fields throw exception"(){
        given:
        Person newPerson = new Person()
        newPerson.with {
            name = newName
            lastName = newLastName
            age = newAge
            phone = newPhone
        }

        when:
        Person person = personService.create(newPerson)

        then:
        thrown ConstraintViolationException

        where:
        newName    |  newLastName   |   newAge  |   newPhone
        ''         | 'Something'    |   20      |   null
        'Something'| ''             |   20      |   null
        'Something'| 'Another Thing'|   0       |   null
        'Something'| 'Another Thing'|   20      |   '12387611'
    }

    def "Find a person by id"(){
        given:
        PersonRepository personRepository = Stub()
        personService = new PersonService(
                personRepository: personRepository
        )


        and:
        personRepository.findById(1) >> Optional.of(new Person([
                id:1,
                name:"some",
                lastName:"thing",
                age: 30
        ]))

        when:

        Person person = personService.findById(1)

        then:
        person.getId() == 1
        person.getName() == "some"
        person.getLastName() == "thing"
        person.getAge() == 30
    }

    def "Delete person throw exception"(){
        when:
        personService.delete(10)

        then:
        thrown RuntimeException
    }

}
