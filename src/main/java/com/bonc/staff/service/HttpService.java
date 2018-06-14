package com.bonc.staff.service;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

public interface HttpService {
    /**
     * 通过post获取json数据
     *
     * @param url 要访问的地址
     * @param params 访问的参数
     * @return json数据
     *
     * */
    String post(String url, Map<String,String> params);
    String postJson(String url, JSONObject param);
}
