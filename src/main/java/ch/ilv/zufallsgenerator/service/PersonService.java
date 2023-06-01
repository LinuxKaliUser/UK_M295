package ch.ilv.zufallsgenerator.service;

import ch.ilv.zufallsgenerator.base.MessageResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import ch.ilv.zufallsgenerator.model.Person;
import ch.ilv.zufallsgenerator.repo.PersonRepo;

import java.util.List;

@Service
public class PersonService {
    private final PersonRepo personRepo;
    private final RandomGenerator randomGenerator;

    public PersonService(PersonRepo personRepo, RandomGenerator randomGenerator) {
        this.personRepo = personRepo;
        this.randomGenerator = randomGenerator;
    }

    public List<Person> saveAllPersons(List<Person> persons) {
        return personRepo.saveAll(persons);
    }


    public Person savePerson(Person Person) {
        return personRepo.save(Person);
    }

    public Person getPerson(Long id) {
        return  personRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException());
    }

    public List<Person> getAllPersonsRandom() {
        List<Person> persons = personRepo.findAll();
        return randomGenerator.getRandomPersonList(persons, personRepo);
    }

    public List<Person> getAllPersons() {
        List<Person> persons = personRepo.findAll();
        return persons;
    }

    public String getTestPersonName() {
        Person Person = new Person();
        Person.setName("TestPerson");
        savePerson(Person);
        return Person.getName();
    }

    public Person updatePerson(Person person, Long id) {
        return personRepo.findById(id)
                .map(personOrig -> {
                    personOrig.setId(person.getId());
                    personOrig.setName(person.getName());
                    personOrig.setRemarks(person.getRemarks());
                    return personRepo.save(personOrig);
                })
                .orElseGet(() -> personRepo.save(person));
    }

    public MessageResponse deletePerson(Long id) {
        personRepo.deleteById(id);
        return new MessageResponse( "Person" + id+" deleted");


    }

    public String deleteAllPersons(List<Person> Persons) {
        try {
            personRepo.deleteAll(Persons);
            return "Deleted all Persons";
        } catch (Throwable throwable) {
            return "Didn't delete all Persons";
        }

    }
}
