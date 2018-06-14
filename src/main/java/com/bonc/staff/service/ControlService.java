package com.bonc.staff.service;

import com.bonc.staff.entity.AddressEntity;
import com.bonc.staff.entity.PerAddEntity;
import com.bonc.staff.entity.PersonEntity;
import com.bonc.staff.pojo.PersonPojo;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author xukj
 */
@Log4j2
@Service
public class ControlService {

    @Autowired
    private AddressService addressService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private PersonService personService;
    @Autowired
    private PerAddService perAddService;

    private int personCount = 0;

    public Integer build() {
        for (AddressEntity ae : addressService.getRegion()) {
            String orderId = orderService.getOrderByCityCodeAndRegionCode(ae.getCode(), ae.getPacode());
            if (orderId != null) {
                List<PersonPojo> persons = personService.getPersonsByOrderId(orderId);
                if (persons != null) {
                    this.savePersonAndPerAdd(persons, ae.getId());
                }
            }
        }
        log.info("本次操作新增人数:{}", personCount);
        return personCount;
    }

    @Transactional(rollbackFor = Exception.class)
    public void savePersonAndPerAdd(List<PersonPojo> persons, String addressId) {

        for (PersonPojo pp : persons) {
            if(perAddService.countPerAdd(pp.getDespatcherPhone(), addressId) == 0) {
                PersonEntity person;
                try {
                    person = personService.savePerson(new PersonEntity(pp.getDespatcherName(), pp.getDespatcherPhone(), pp.getDespatcherAddr(), pp.getAmount()));
                    personCount++;
                } catch(DataIntegrityViolationException e) {
                    log.info("人员已经存在");
                    person = personService.findByPhone(pp.getDespatcherPhone());
                }
                perAddService.save(new PerAddEntity(person.getId(), addressId));
            }
        }

        //for (PersonPojo pp : persons) {
        //    PersonEntity person = personService.findByPhone(pp.getDespatcherPhone());
        //    if (null == person) {
        //        person = personService.savePerson(new PersonEntity(pp.getDespatcherName(), pp.getDespatcherPhone(), pp.getDespatcherAddr(), pp.getAmount()));
        //        perAddService.save(new PerAddEntity(person.getId(), addressId));
        //        personCount++;
        //        continue;
        //    }
        //    if (null == perAddService.findByPersonidAndAddressid(person.getId(), addressId)) {
        //        perAddService.save(new PerAddEntity(person.getId(), addressId));
        //    }
        //}
    }
}
