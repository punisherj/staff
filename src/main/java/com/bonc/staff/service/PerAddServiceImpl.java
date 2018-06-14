package com.bonc.staff.service;

import com.bonc.staff.entity.PerAddEntity;
import com.bonc.staff.repository.PerAddRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xukj
 */
@Service
public class PerAddServiceImpl implements PerAddService{

    @Autowired
    private PerAddRepository perAddRepository;

    @Override
    public PerAddEntity findByPersonidAndAddressid(String personId, String addressId) {
        return perAddRepository.findByPersonidAndAddressid(personId, addressId);
    }

    @Override
    public void save(PerAddEntity pe) {
        perAddRepository.save(pe);
    }

    @Override
    public Integer countPerAdd(String personPhone, String addressId) {
        return perAddRepository.countByPersonPhoneAndAddressId(personPhone, addressId);
    }
}
