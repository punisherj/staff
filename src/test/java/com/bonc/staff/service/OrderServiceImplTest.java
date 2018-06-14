package com.bonc.staff.service;

import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Log4j2
@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderServiceImplTest {
    @Autowired
    OrderService orderService;
    @Test
    public void test() {
       String id =  orderService.getOrderByCityCodeAndRegionCode("210123","210100");
       log.info("orderid:"+id);
    }
}