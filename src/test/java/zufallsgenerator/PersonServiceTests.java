package zufallsgenerator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import zufallsgenerator.model.Person;
import zufallsgenerator.repo.PersonRepo;
import zufallsgenerator.service.PersonService;
import zufallsgenerator.service.RandomGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PersonServiceTest {

    private PersonService personService;

    @Mock
    private PersonRepo personRepoMock;

    @Mock
    private RandomGenerator randomGeneratorMock;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        personService = new PersonService(personRepoMock, randomGeneratorMock);
    }

    @Test
    public void saveAllPersons_shouldReturnListOfPersons() {
        Person person1 = new Person();
        Person person2 = new Person();
        person1.setName("Alice");
        person2.setName("Bob");
        List<Person> people = Arrays.asList(person1, person2);
        when(personRepoMock.saveAll(people)).thenReturn(people);

        List<Person> result = personService.saveAllPersons(people);

        assertEquals(people, result);
        verify(personRepoMock, times(1)).saveAll(people);
    }

    @Test
    public void savePerson_shouldReturnSavedPerson() {
        Person person = new Person("Alice");
        when(personRepoMock.save(person)).thenReturn(person);

        Person result = personService.savePerson(person);

        assertEquals(person, result);
        verify(personRepoMock, times(1)).save(person);
    }

    @Test
    public void getPerson_shouldReturnRandomPerson() {
        List<Person> people = Arrays.asList(new Person("Alice"), new Person("Bob"));
        Person randomPerson = new Person("Alice");
        when(personRepoMock.findAll()).thenReturn(people);
        when(randomGeneratorMock.getRandomPerson(people, personRepoMock)).thenReturn(randomPerson);

        Person result = personService.getPerson();

        assertEquals(randomPerson, result);
        verify(personRepoMock, times(1)).findAll();
        verify(randomGeneratorMock, times(1)).getRandomPerson(people, personRepoMock);
    }

    @Test
    public void getAllPersons_shouldReturnRandomListOfPersons() {
        List<Person> people = Arrays.asList(new Person("Alice"), new Person("Bob"));
        List<Person> randomPeople = Arrays.asList(new Person("Bob"), new Person("Alice"));
        when(personRepoMock.findAll()).thenReturn(people);
        when(randomGeneratorMock.getRandomPersonList(people, personRepoMock)).thenReturn(randomPeople);

        List<Person> result = personService.getAllPersons();

        assertEquals(randomPeople, result);
        verify(personRepoMock, times(1)).findAll();
        verify(randomGeneratorMock, times(1)).getRandomPersonList(people, personRepoMock);
    }

    @Test
    public void getTestPersonName_shouldReturnTestPersonName() {
        Person person = new Person("TestPerson");
        when(personRepoMock.save(any())).thenReturn(person);

        String result = personService.getTestPersonName();

        assertEquals("TestPerson", result);
        verify(personRepoMock, times(1)).save(any());
    }

    @Test
    public void updatePerson_shouldReturnUpdatedPerson() {
        Long id = 1L;
        Person person = new Person("Alice", "Remarks");
        Person existingPerson = new Person("Bob");
        existingPerson.setId(id);
        when(personRepoMock.findById(id)).thenReturn(Optional.of(existingPerson));
        when(personRepoMock.save(existingPerson)).thenReturn(existingPerson);

        Person result = personService
