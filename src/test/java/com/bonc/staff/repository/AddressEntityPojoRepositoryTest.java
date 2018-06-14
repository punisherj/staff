package com.bonc.staff.repository;

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
public class AddressEntityPojoRepositoryTest {

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    AddressService addressService;

    @Test
    public void testSave() {
        AddressEntity a = new AddressEntity("测试","123","0");
        addressRepository.save(a);
    }

    @Test
    public void query() {
        List<AddressEntity> addrs = addressService.getPaAddressCodes();
        addrs.stream().forEach(n -> n.getCode());
    }
}