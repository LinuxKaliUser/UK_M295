package ch.ilv.zufallsgenerator;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.client.RestTemplate;
import ch.ilv.zufallsgenerator.model.Person;
import ch.ilv.zufallsgenerator.repo.PersonRepo;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
@AutoConfigureDataJpa
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PersonControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PersonRepo personRepo;

    @BeforeEach
    public void setUp() {
        List<Person> persons = new ArrayList<>();
        Person p1 = new Person();
        p1.setName("Alice");
        Person p2 = new Person();
        p2.setName("Bob");
        persons.add(p1);
        persons.add(p2);
        personRepo.saveAll(persons);
    }

    @AfterEach
    public void tearDown() {
        personRepo.deleteAll(personRepo.findAll());
    }

    @Test
    public void testGetPersonName() throws Exception {
        String accessToken = obtainAccessToken();
        mockMvc.perform(get("/testperson").header("Authorization", "Bearer " + accessToken)
                        .with(csrf()))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("TestPerson")));
    }

    @Test
    public void testUpdatePerson() throws Exception {
        String accessToken = obtainAccessToken();
        List<Person> allPersons = personRepo.findAll();
        Person personToUpdate = allPersons.get(0);
        personToUpdate.setName("UpdatedName");
        //personToUpdate.setRemarks("UpdatedRemark");
        String jsonBody = new ObjectMapper().writeValueAsString(personToUpdate);
        mockMvc.perform(put("/person/{id}", personToUpdate.getId())
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + accessToken)
                        .with(csrf()))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("UpdatedName")));
    }

    @Test
    public void testSavePerson() throws Exception {
        String accessToken = obtainAccessToken();
        Person newPerson = new Person();
        newPerson.setName("NewPerson");

        String json = new ObjectMapper().writeValueAsString(newPerson);
        mockMvc.perform(post("/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .header("Authorization", "Bearer " + accessToken)
                        .with(csrf()))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString(newPerson.getName())));

    }

    @Test
    void testDeletePerson() throws Exception {
        String accessToken = obtainAccessToken();
        Person person = new Person();
        person.setName("TestPerson");
        person = personRepo.save(person);

        MvcResult mvcResult = mockMvc.perform(delete("/person/" + person.getId()).header("Authorization", "Bearer " + accessToken)
                .with(csrf())).andReturn();

        assertEquals("Deleted" + person.getName(), mvcResult.getResponse().getContentAsString());
        assertFalse(personRepo.findById(person.getId()).isPresent());
    }

    private String obtainAccessToken() {
        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        String body = "client_id=zuffallsgeneratorApp&" +
                "grant_type=password&" +
                "scope=openid profile roles offline_access&" +
                "username=admin&" +
                "password=asdf";

        HttpEntity<String> entity = new HttpEntity<>(body, headers);
        ResponseEntity<String> resp = rest.postForEntity("http://localhost:8080/realms/ILV/protocol/openid-connect/token", entity, String.class);
        JacksonJsonParser jsonParser = new JacksonJsonParser();
        return jsonParser.parseMap(resp.getBody()).get("access_token").toString();
    }
}