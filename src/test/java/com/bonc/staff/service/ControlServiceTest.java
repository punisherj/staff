package com.bonc.staff.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ControlServiceTest {
    @Autowired
    ControlService controlService;
    //@Test
    public void build() {
        controlService.build();
    }

    @Test
    public void testSavePersonAndPerAdd(){
        controlService.savePersonAndPerAdd(null, null);
    }
}