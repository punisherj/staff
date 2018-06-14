package com.bonc.staff.service;

import com.bonc.staff.entity.PersonEntity;
import com.bonc.staff.pojo.PersonPojo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class PersonServiceImplTest {
    @Autowired
    PersonService personService;
    //@Test
    public void testGetPerson() {
        List<PersonPojo> persons = personService.getPersonsByOrderId("1718042747209366");
        persons.stream().forEach(System.out::println);
        Assert.assertEquals(persons.size(), 54);
    }
    //@Test
    public void testSavePerson() {
        PersonEntity p = personService.savePerson(new PersonEntity("xukj","123","沈阳","2"));
        System.out.println("id的值是 ----- " + p.getId() + " ，当前方法为PersonServiceImplTest.testsavePerson()");
    }

    //@Test
    public void testupdatePersonLongLat() {
        personService.updatePersonLongLat();
    }
}