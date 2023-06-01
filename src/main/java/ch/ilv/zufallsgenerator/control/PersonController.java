package ch.ilv.zufallsgenerator.control;

import ch.ilv.zufallsgenerator.base.MessageResponse;
import ch.ilv.zufallsgenerator.model.Person;
import ch.ilv.zufallsgenerator.service.PersonService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ch.ilv.zufallsgenerator.security.Roles;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@SecurityRequirement(name = "bearerAuth")
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/person")
    @RolesAllowed(Roles.Read)
    public ResponseEntity<Person> getPerson() {
        return new ResponseEntity<>(personService.getPerson(), HttpStatus.OK);
    }

    @GetMapping("/persons")
    @RolesAllowed(Roles.Read)
    public ResponseEntity<List<Person>> getAllPersons() {
        List<Person> allPersons = personService.getAllPersons();
        return new ResponseEntity<>(allPersons, HttpStatus.OK);
    }

    @GetMapping("/testperson")
    @RolesAllowed(Roles.Read)
    public String getPersonName() {
        return personService.getTestPersonName();
    }

    @PutMapping("/person/{id}")
    @RolesAllowed(Roles.Update)
    public ResponseEntity<Person> updatePerson(@RequestBody Person person, @PathVariable Long id) {
        Person updatedPerson = personService.updatePerson(person, id);
        return new ResponseEntity<>(updatedPerson, HttpStatus.OK);
    }

    @PostMapping("/person")
    @RolesAllowed(Roles.Update)
    public ResponseEntity<Person> savePerson(@RequestBody Person person) {
        Person savedPerson = personService.savePerson(person);
        return new ResponseEntity<>(savedPerson, HttpStatus.OK);
    }

    @PostMapping("/persons")
    @RolesAllowed(Roles.Update)
    public ResponseEntity<List<Person>> savePersons(@RequestBody List<Person> persons) {
        List<Person> savedAllPersons = personService.saveAllPersons(persons);
        return new ResponseEntity<>(savedAllPersons, HttpStatus.OK);
    }

    @DeleteMapping("/person/{id}")
    @RolesAllowed(Roles.Admin)
    public ResponseEntity<MessageResponse> deletePerson(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(personService.deletePerson(id));
        } catch (Throwable t) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/person")
    @RolesAllowed(Roles.Admin)
    public String deleteAllPersons(@RequestBody List<Person> persons) {
        return personService.deleteAllPersons(persons);
    }

}
