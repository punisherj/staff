package com.bonc.staff.service;

import com.alibaba.fastjson.JSONObject;
import com.bonc.staff.entity.PersonEntity;
import com.bonc.staff.map.pojo.MapPoint;
import com.bonc.staff.pojo.PersonPojo;
import com.bonc.staff.repository.PersonRepository;
import com.bonc.staff.util.GisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xukj
 */
@Slf4j
@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    HttpService httpService;
    @Autowired
    PersonRepository personRepository;

    @Override
    public List<PersonPojo> getPersonsByOrderId(String orderId) {
        String url = "http://admin.mall.10010.com/Acodm/OrderRelease/getDespatcherList";
        Map<String, Object> map = new HashMap<>(4);
        map.put("currPage","1");
        map.put("pageSize","999999");
        map.put("template","order/orderrelease/despatcherstable");
        map.put("orderId", orderId);
        String resp = httpService.postJson(url, new JSONObject(map));
        JSONObject object = JSONObject.parseObject(resp);
        if(null != object) {
            String pages = object.getString("despatchers");
            return JSONObject.parseArray(pages, PersonPojo.class);
        }
        log.error("获取人员列表为null:" + resp);
        return null;
    }

    @Override
    public PersonEntity savePerson(PersonEntity pe) {
        return personRepository.save(pe);
    }

    @Override
    public PersonEntity findByPhone(String phone) {
        return personRepository.findByPhone(phone);
    }

    @Override
    public void updatePersonLongLat() {
        int count = 0;
        List<PersonEntity> persons = personRepository.findByLongLatIsNull();
        for(PersonEntity person: persons) {
            MapPoint mp = null;
            try {
                String address = person.getAddress().contains(" ")?person.getAddress().replace(" ",""):person.getAddress();
                //Thread.sleep(1000);
                mp = GisUtil.convertGeocode(address);
            } catch (Exception e) {
                log.error(e.toString());
                continue;
            }
            if(null != mp) {
                person.setLongLat(mp.getLongitude() + "," + mp.getLatitude());
            } else {
                person.setLongLat("0");
            }
            personRepository.save(person);
            count++;
            NumberFormat nt = NumberFormat.getPercentInstance();
            nt.setMinimumFractionDigits(0);
            log.info(nt.format((float)count/persons.size()));
        }
    }
}
