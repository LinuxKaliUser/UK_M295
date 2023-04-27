package zufallsgenerator.service;

import org.springframework.stereotype.Service;
import zufallsgenerator.model.Person;
import zufallsgenerator.repo.PersonRepo;

import java.util.List;

@Service
public class PersonService {
    private final PersonRepo personRepo;

    public  PersonService(PersonRepo personRepo){
        this.personRepo = personRepo;
    }
    public List<Person> saveAllPersons(List<Person> People) {
        return personRepo.saveAll(People);
    }


    public Person savePerson( Person Person){
        return personRepo.save(Person);
    }
    public Person getPerson(){
        List<Person> persons = personRepo.findAll();
        return RandomGenerator.getRandomPersonList(persons, true).get(1);
    }
    public List<Person> getAllPersons(){
        List<Person> persons = personRepo.findAll();
        return RandomGenerator.getRandomPersonList(persons, false);
    }
    public String getTestPersonName(){
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

    public String deletePerson(Long id) {
        Person Person = personRepo.findById(id).get();
        try {
            personRepo.deleteById(id);
            return "Deleted"+Person.getName();
        }catch(Throwable throwable) {
            return "Didn't delete "+Person.getName();
        }

    }

    public String deleteAllPersons(List<Person> Persons) {
        try {
            personRepo.deleteAll(Persons);
            return "Deleted all Persons";
        }catch(Throwable throwable) {
            return "Didn't delete all Persons" ;
        }

    }
}
