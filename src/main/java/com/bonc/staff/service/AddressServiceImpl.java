package com.bonc.staff.service;

import com.alibaba.fastjson.JSON;
import com.bonc.staff.entity.AddressEntity;
import com.bonc.staff.pojo.AddressPojo;
import com.bonc.staff.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xukj
 */
@Service
public class AddressServiceImpl implements AddressService {

    private final String CITY = "0";

    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private HttpService httpService;

    @Override
    public List<AddressEntity> getPaAddressCodes() {
        return addressRepository.findByPacode("0");
    }

    @Override
    public void saveRegionByCityCode(String cityCode) {
        List<AddressPojo> address = this.getAddressByCityCode(cityCode);
        address.forEach(n -> addressRepository.save(new AddressEntity(n.getDISTRICT_NAME(),n.getDISTRICT_CODE(), n.getCITY_CODE())));
    }

    @Override
    public List<AddressPojo> getAddressByCityCode(String cityCode) {
        String url = "http://admin.mall.10010.com/Acodm/OrderRelease/getDistricts";
        Map<String, String> map = new HashMap<String, String>(1) {{put("cityCode", cityCode); }};
        String resp = httpService.post(url, map);
        return JSON.parseArray(resp, AddressPojo.class);
    }

    @Override
    public List<AddressEntity> getRegion() {
        return addressRepository.findByPacodeNot(CITY);
    }
}
