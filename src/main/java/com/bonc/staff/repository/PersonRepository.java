package com.bonc.staff.repository;

import com.bonc.staff.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author xukj
 */
public interface PersonRepository extends JpaRepository<PersonEntity, Long> {

    /**
     * 通过电话查询
     * @param phone
     * @return
     */
    PersonEntity findByPhone(String phone);

    /**
     * 查询经纬度为空的数据
     * @return
     */
    List<PersonEntity> findByLongLatIsNull();
}
