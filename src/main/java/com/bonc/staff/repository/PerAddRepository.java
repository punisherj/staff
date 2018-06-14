package com.bonc.staff.repository;

import com.bonc.staff.entity.PerAddEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author xukj
 */
public interface PerAddRepository extends JpaRepository<PerAddEntity ,Long> {
    /**
     * 查询数据库中是否已经存在相同记录
     * @param personId
     * @param addressId
     * @return
     */
    PerAddEntity findByPersonidAndAddressid(String personId, String addressId);

    @Query(value = "select count(*) from staff_person_address a left join staff_person b on a.personid = b.id where b.phone=?1 and a.addressid=?2 ", nativeQuery=true)
    Integer countByPersonPhoneAndAddressId(String phone, String addressId);
}
