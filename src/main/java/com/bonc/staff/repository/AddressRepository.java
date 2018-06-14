package com.bonc.staff.repository;

import com.bonc.staff.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author xukj
 */
public interface AddressRepository extends JpaRepository<AddressEntity,Long> {
    /**
     * 查询所有市
     * @param pacode 0代表是市
     * @return 所有市
     * */
    List<AddressEntity> findByPacode(String pacode);

    /***
     *
     * @param pacode 固定传0
     * @return 所以区级地区
     */
    List<AddressEntity> findByPacodeNot(String pacode);
}
