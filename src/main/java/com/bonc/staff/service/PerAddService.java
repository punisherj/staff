package com.bonc.staff.service;

import com.bonc.staff.entity.PerAddEntity;

/**
 * @author xukj
 */
public interface PerAddService {
    /**
     * 通过人员id和地址id查询关系
     * @param personId
     * @param addressId
     * @return
     */
    PerAddEntity findByPersonidAndAddressid(String personId, String addressId);

    /**
     * 保存人和地址的关系
     * @param pe
     */
    void save(PerAddEntity pe);

    Integer countPerAdd(String personPhone, String addressId);
}
