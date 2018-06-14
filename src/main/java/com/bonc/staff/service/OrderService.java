package com.bonc.staff.service;

public interface OrderService {
    /***
     *
     * @param code 市级代码
     * @param pacode 区级代码
     * @return 区的订单id
     */
    String getOrderByCityCodeAndRegionCode(String code, String pacode);
}
