package zufallsgenerator.control;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import zufallsgenerator.model.Person;
import zufallsgenerator.model.Person;
import zufallsgenerator.security.Roles;
import zufallsgenerator.service.PersonService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@SecurityRequirement(name = "bearerAuth")
public class PersonController {
    private final PersonService personService;

    public  PersonController(PersonService personService){
        this.personService = personService;
    }
    @GetMapping("/Person/{id}")
    @RolesAllowed(Roles.Read)
    public String getPersonById(@PathVariable Long id){
        return personService.getPersonById(id);
    }
    @GetMapping("/Person")
    @RolesAllowed(Roles.Read)
    public String getPersonById(){
        return personService.getAllPersons();
    }

    @GetMapping("/TestPerson")
    public String getPersonName(){
        return personService.getTestPersonName();
    }
    @PutMapping("/Person/{id}")
    public ResponseEntity<Person> updatePerson(@RequestBody Person person, @PathVariable Long id){
        Person updatedPerson=personService.updatePerson(person,id);
        return new ResponseEntity<>(updatedPerson, HttpStatus.OK);
    }
    @PostMapping("/Person")
    public String savePerson(@RequestBody Person person){
        return personService.savePerson(person);
    }
    @PostMapping("/Person")
    public String savePersonen(@RequestBody List<Person> people){
        return  personService.saveAllPersons(people);
    }
    @DeleteMapping("/Person/{id}")
    public  String deletePerson(@PathVariable Long id){
        return  personService.deletePerson(id);
    }
    @DeleteMapping("/Person")
    public  String deleteAllPersons(@RequestBody List<Person> people){
        return  personService.deleteAllPersons(people);
    }
    
}
