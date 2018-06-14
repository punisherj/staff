package com.bonc.staff.service;

import com.bonc.staff.entity.AddressEntity;
import com.bonc.staff.pojo.AddressPojo;

import java.util.List;

/**
 * @author xukj
 */
public interface AddressService {
    /**
     * 获取所有市
     * @return 所有市
     * */
    List<AddressEntity> getPaAddressCodes();

    /**
     * 通过市的代码保存区级地区信息
     * @param cityCode 市代码
     * */
    void saveRegionByCityCode(String cityCode);

    /**
     * 通过市代码获得区级地区信息
     * @param cityCode 市代码
     * @return 区级地区信息
     * */
    List<AddressPojo> getAddressByCityCode(String cityCode);
    /**
     *
     * 获取所有区级城市
     */
    List<AddressEntity> getRegion();
}
