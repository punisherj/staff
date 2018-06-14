package com.bonc.staff.service;

import com.alibaba.fastjson.JSONObject;
import com.bonc.staff.pojo.PagesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xukj
 */
@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    private HttpService httpService;
    @Override
    public String getOrderByCityCodeAndRegionCode(String code, String pacode) {
        String url = "http://admin.mall.10010.com/Acodm/OrderRelease/queryOrders";
        Map<String, Object> map = new HashMap<>(8);
        map.put("currPage","1");
        map.put("pageSize","5");
        map.put("template","order/orderrelease/orderreleasetable");
        map.put("orderNo","");
        map.put("releaseState","-全部-");
        map.put("postAddr","");
        map.put("cityCode", pacode);
        map.put("districtCode", code);
        String resp = httpService.postJson(url, new JSONObject(map));
        JSONObject object = JSONObject.parseObject(resp);
        String pages = object.getString("pages");
        List<PagesDTO> pagesDTO = JSONObject.parseArray(pages, PagesDTO.class);
        if(pagesDTO.size() == 0) {
            return null;
        }
        return pagesDTO.get(0).getOrderId();
    }
}
