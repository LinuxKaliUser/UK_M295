package zufallsgenerator;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import zufallsgenerator.model.DateSetting;
import zufallsgenerator.model.Person;
import zufallsgenerator.model.Remarks;
import zufallsgenerator.model.Team;
import zufallsgenerator.repo.PersonRepo;

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
    /*@Test
    void insertPerson(){
        Person personTest1 = new Person();
        personTest1.setName("TestRichard");
        personTest1.setSequence(1);
        personTest1.setTask("TestTask");

        DateSetting dateSetting = new DateSetting();
        dateSetting.setDateTimeNonFormatted("12Uhr 27.04.23");

        Remarks remarks=new Remarks();
        remarks.setContent("TestRemarks");

        Team team = new Team();
        team.setName("TestTeam");

        personTest1.setDateSetting(dateSetting);
        personTest1.setRemarks(remarks);
        //personTest1.setTeam(team);

        Person personTest=this.personRepo.save(personTest1);
        Assertions.assertNotNull(personTest.getId());


    }*/
    @Test
    @Transactional
    public void testSavePerson() {
        // given
        Person person = new Person();
        person.setName("Test Person");

        // when
        Person savedPerson = personRepo.save(person);

        // then
        Assertions.assertNotNull(savedPerson.getId());
        Assertions.assertEquals(person.getName(), savedPerson.getName());
    }

    @Test
    @Transactional
    public void testGetPersonById() {
        // given
        Person person = new Person();
        person.setName("Test Person");
        Person savedPerson = personRepo.save(person);

        // when
        Person retrievedPerson = personRepo.findById(savedPerson.getId()).orElse(null);

        // then
        Assertions.assertNotNull(retrievedPerson);
        Assertions.assertEquals(savedPerson.getId(), retrievedPerson.getId());
        Assertions.assertEquals(savedPerson.getName(), retrievedPerson.getName());
    }

    @Test
    @Transactional
    public void testUpdatePerson() {
        // given
        Person person = new Person();
        person.setName("Test Person");
        Person savedPerson = personRepo.save(person);

        // when
        savedPerson.setName("Updated Person");
        Person updatedPerson = personRepo.save(savedPerson);

        // then
        Assertions.assertEquals(savedPerson.getId(), updatedPerson.getId());
        Assertions.assertEquals("Updated Person", updatedPerson.getName());
    }

    @Test
    @Transactional
    public void testDeletePerson() {
        // given
        Person person = new Person();
        person.setName("Test Person");
        Person savedPerson = personRepo.save(person);

        // when
        personRepo.deleteById(savedPerson.getId());

        // then
        Assertions.assertFalse(personRepo.findById(savedPerson.getId()).isPresent());
    }

    @Test
    @Transactional
    public void testGetAllPersons() {
        // given
        Person person1 = new Person();
        person1.setName("Person 1");
        personRepo.save(person1);

        Person person2 = new Person();
        person2.setName("Person 2");
        personRepo.save(person2);

        // when
        List<Person> allPersons = personRepo.findAll();

        // then
        Assertions.assertEquals(2, allPersons.size());
    }
}
