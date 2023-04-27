package zufallsgenerator;

import org.junit.jupiter.api.Assertions;
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

@DataJpaTest()
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class DBTests {

    @Autowired
    private PersonRepo personRepo;

    @Test
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
        personTest1.setTeam(team);

        Person personTest=this.personRepo.save(personTest1);
        Assertions.assertNotNull(personTest.getId());


    }
}
