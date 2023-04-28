package zufallsgenerator;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import zufallsgenerator.model.Person;
import zufallsgenerator.service.PersonService;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.RequestEntity.post;
import static org.springframework.http.RequestEntity.put;
import static org.springframework.test.util.AssertionErrors.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@Transactional
public class PersonControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PersonService personService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        List<Person> persons = new ArrayList<>();
        Person p1 = new Person();
        p1.setName("Alice");
        p1.setRemarks("remark1");
        Person p2 = new Person();
        p2.setName("Bob");
        p2.setRemarks("remark2");
        persons.add(p1);
        persons.add(p2);
        personService.saveAllPersons(persons);
    }

    @AfterEach
    public void tearDown() {
        personService.deleteAllPersons(personService.getAllPersons());
    }

    @Test
    public void testGetPerson() throws Exception {
        mockMvc.perform(get("/person"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").isNotEmpty());
    }

    @Test
    public void testGetAllPersons() throws Exception {
        mockMvc.perform(get("/persons"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[*].name").isNotEmpty());
    }

    @Test
    public void testGetPersonName() throws Exception {
        mockMvc.perform(get("/testperson"))
                .andExpect(status().isOk())
                .andExpect(content().string("TestPerson"));
    }

    @Test
    public void testUpdatePerson() throws Exception {
        List<Person> allPersons = personService.getAllPersons();
        Person personToUpdate = allPersons.get(0);
        personToUpdate.setName("UpdatedName");
        personToUpdate.setRemarks("UpdatedRemark");
        String json = objectMapper.writeValueAsString(personToUpdate);
        mockMvc.perform(put("/person/{id}", personToUpdate.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(personToUpdate.getName()))
                .andExpect(jsonPath("$.remarks").value(personToUpdate.getRemarks()));
    }

    @Test
    public void testSavePerson() throws Exception {
        Person newPerson = new Person();
        newPerson.setName("NewPerson");

        newPerson.setRemarks("NewRemark");
        String json = objectMapper.writeValueAsString(newPerson);
        mockMvc.perform(post("/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(newPerson.getName()))
                .andExpect(jsonPath("$.remarks").value(newPerson.getRemarks()));
    }

    @Test
    public void testSaveAllPersons() throws Exception {
        List<Person> newPersons = new ArrayList<>();
        Person p1 = new Person();
        p1.setName("NewPerson1");
        p1.setRemarks("NewRemark1");
        Person p2 = new Person();
        p2.setName("NewPerson2");
        p2.setRemarks("NewRemark2");
        newPersons.add(p1);
        newPersons.add(p2);
        String json = objectMapper.writeValueAsString(newPersons);
        mockMvc.perform((RequestBuilder) post("/persons")
                        .contentType(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.valueOf(json)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[*].name", hasItems("NewPerson1", "NewPerson2")))
                .andExpect(jsonPath("$.[*].remarks", hasItems("NewRemark1", "NewRemark2")));
    }

    @Test
    void deletePerson_ShouldReturnDeletedPersonName_WhenValidId() throws Exception {
        // Arrange
        Person person = new Person();
        person.setName("TestPerson");
        person = personService.savePerson(person);

        // Act
        MvcResult mvcResult = mockMvc.perform(delete("/person/" + person.getId()))
                .andExpect(status().isOk())
                .andReturn();

        // Assert
        assertEquals("Deleted" + person.getName(), mvcResult.getResponse().getContentAsString());
        //assertFalse("Couldn't Find it",r);
    }

    @Test
    void deletePerson_ShouldReturnError_WhenInvalidId() throws Exception {
        // Arrange
        Long invalidId = 100L;

        // Act
        MvcResult mvcResult = mockMvc.perform(delete("/person/" + invalidId))
                .andExpect(status().isOk())
                .andReturn();

        // Assert
        assertEquals("Didn't delete Person", mvcResult.getResponse().getContentAsString());
    }
}