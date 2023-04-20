package zufallsgenerator.control;

import zufallsgenerator.model.Person;
import zufallsgenerator.repo.IPersonRepo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {
    private final IPersonRepo personRepo;

    public  PersonController(IPersonRepo personRepo){
        this.personRepo = personRepo;
    }
    @PostMapping("/SavePerson")
    public String savePerson(@RequestBody Person person){
        personRepo.save(person);
        return "%s saved!".formatted(person.getName());
    }
    @PostMapping("/SavePersonen")
    public String savePersonen(@RequestBody List<Person> persons){
        try {
            personRepo.saveAll(persons);
            return "Saved all Person!";
        } catch (Exception e) {
            return e.toString();
        }
    }
    @GetMapping("/GetPersonById/{id}")
    //@RolesAllowed(Roles.Read)
    public String getPersonById(@PathVariable Long id){
        Person person = personRepo.findById(id).get();
        return person.getName();
    }
    @GetMapping("/GetPersonen")
    //@RolesAllowed(Roles.Read)
    public String getPersonById(){
        List<Person> personen = personRepo.findAll();
        StringBuilder result = new StringBuilder();
        for (Person person : personen) {
            result.append(person.getName());
            result.append(", ");
        }
        return result.toString();
    }

    @GetMapping("/TestPerson")
    public String getPersonName(){
        Person p1 = new Person();
        p1.setName("Testg");
        savePerson(p1);
        return p1.getName();
    }
}
