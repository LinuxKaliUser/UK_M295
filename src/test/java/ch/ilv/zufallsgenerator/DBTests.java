package ch.ilv.zufallsgenerator;

import ch.ilv.zufallsgenerator.model.Person;
import ch.ilv.zufallsgenerator.repo.PersonRepo;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@DataJpaTest()
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class DBTests {

    @Autowired
    private PersonRepo personRepo;

    @AfterEach
    public void tearDown() {
        personRepo.deleteAll(personRepo.findAll());
    }

    @Test
    @Transactional
    public void testSavePerson() {
        Person person = new Person();
        person.setName("Test Person");
        Person savedPerson = personRepo.save(person);
        Assertions.assertNotNull(savedPerson.getId());
        Assertions.assertEquals(person.getName(), savedPerson.getName());
    }

    @Test
    @Transactional
    public void testGetPersonById() {
        Person person = new Person();
        person.setName("Test Person");
        Person savedPerson = personRepo.save(person);
        Person retrievedPerson = personRepo.findById(savedPerson.getId()).orElse(null);
        Assertions.assertNotNull(retrievedPerson);
        Assertions.assertEquals(savedPerson.getId(), retrievedPerson.getId());
        Assertions.assertEquals(savedPerson.getName(), retrievedPerson.getName());
    }

    @Test
    @Transactional
    public void testUpdatePerson() {
        Person person = new Person();
        person.setName("Test Person");
        Person savedPerson = personRepo.save(person);
        savedPerson.setName("Updated Person");
        Person updatedPerson = personRepo.save(savedPerson);
        Assertions.assertEquals(savedPerson.getId(), updatedPerson.getId());
        Assertions.assertEquals("Updated Person", updatedPerson.getName());
    }

    @Test
    @Transactional
    public void testDeletePerson() {
        Person person = new Person();
        person.setName("Test Person");
        Person savedPerson = personRepo.save(person);
        personRepo.deleteById(savedPerson.getId());
        Assertions.assertFalse(personRepo.findById(savedPerson.getId()).isPresent());
    }

    @Test
    @Transactional
    public void testGetAllPersons() {
        Person person1 = new Person();
        person1.setName("Person 1");
        personRepo.save(person1);
        Person person2 = new Person();
        person2.setName("Person 2");
        personRepo.save(person2);
        List<Person> allPersons = personRepo.findAll();
        Assertions.assertEquals(2, allPersons.size());
    }
}
