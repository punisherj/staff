package com.bonc.staff.controller;

import com.bonc.staff.config.CookieConfig;
import com.bonc.staff.entity.AddressEntity;
import com.bonc.staff.service.AddressService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class IndexControllerTest {

    @Autowired
    private AddressService addressService;
    @Autowired
    private CookieConfig cookieConfig;

    /**
     * 保存所有不是市的地址
     * */
    @Test
    public void saveAddress() {
        addressService.getPaAddressCodes().forEach(n->addressService.saveRegionByCityCode(n.getCode()));
    }

    @Test
    public void testCookieConfig() {
        String cookie = cookieConfig.getCookie();
        System.out.println("cookie的值是 ----- " + cookie + " ，当前方法为IndexControllerTest.testCookieConfig()");
    }
}