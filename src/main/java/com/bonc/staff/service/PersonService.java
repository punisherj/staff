package com.bonc.staff.service;

import com.bonc.staff.entity.PersonEntity;
import com.bonc.staff.pojo.PersonPojo;

import java.util.List;

/**
 * @author xukj
 */
public interface PersonService {
    /***
     * 获取该订单id下所有可以分配的人员
     * @param orderId
     * @return 人员列表
     */
    List<PersonPojo> getPersonsByOrderId(String orderId);

    /***
     * 保存人员信息
     * @param pe
     */
    PersonEntity savePerson(PersonEntity pe);

    /**
     * 按电话号码查询人员
     * @param phone
     * @return 人员信息
     */
    PersonEntity findByPhone(String phone);

    /**
     * 增加人员信息坐标字段
     */
    void updatePersonLongLat();
}
