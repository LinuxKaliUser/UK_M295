package zufallsgenerator.control;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import zufallsgenerator.model.Meal;
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
    @GetMapping("/person/{id}")
    @RolesAllowed(Roles.Read)
    public ResponseEntity<Person> getPersonById(@PathVariable Long id){
        Person personById = personService.getPersonById(id);
        return new ResponseEntity<>(personById, HttpStatus.OK);
    }
    @GetMapping("/person")
    @RolesAllowed(Roles.Read)
    public ResponseEntity<List<Person>> getPersonById(){
        List<Person> allPersons = personService.getAllPersons();
        return new ResponseEntity<>(allPersons, HttpStatus.OK) ;
    }

    @GetMapping("/testperson")
    @RolesAllowed(Roles.Read)
    public String getPersonName(){
        return personService.getTestPersonName();
    }
    @PutMapping("/person/{id}")
    @RolesAllowed(Roles.Update)
    public ResponseEntity<Person> updatePerson(@RequestBody Person person, @PathVariable Long id){
        Person updatedPerson=personService.updatePerson(person,id);
        return new ResponseEntity<>(updatedPerson, HttpStatus.OK);
    }
    @PostMapping("/person")
    @RolesAllowed(Roles.Update)
    public ResponseEntity<Person> savePerson(@RequestBody Person person){
        Person savedPerson = personService.savePerson(person);
        return new ResponseEntity<>(savedPerson, HttpStatus.OK);
    }
    @PostMapping("/persons")
    @RolesAllowed(Roles.Update)
    public ResponseEntity<List<Person>> savePersons(@RequestBody List<Person> people){
        List<Person> savedAllPersons = personService.saveAllPersons(people);
        return new ResponseEntity<>(savedAllPersons, HttpStatus.OK);
    }
    @DeleteMapping("/person/{id}")
    @RolesAllowed(Roles.Admin)
    public  String deletePerson(@PathVariable Long id){
        return  personService.deletePerson(id);
    }
    @DeleteMapping("/person")
    @RolesAllowed(Roles.Admin)
    public  String deleteAllPersons(@RequestBody List<Person> people){
        return  personService.deleteAllPersons(people);
    }
    
}
