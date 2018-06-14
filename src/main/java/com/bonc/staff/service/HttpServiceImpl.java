package com.bonc.staff.service;

import com.alibaba.fastjson.JSONObject;
import com.bonc.staff.config.CookieConfig;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author xukj
 */
@Service
public class HttpServiceImpl implements HttpService {
    private static final int SUCCESS = 200;
    private static final int MULTIPLE_CHOICES = 300;

    @Autowired
    private CookieConfig cookieConfig;

    @Override
    public String post(String url, Map<String, String> params) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        List<NameValuePair> nvps = new ArrayList<>();

        params.forEach((k, v) ->
                nvps.add(new BasicNameValuePair(k, v))
        );
        CloseableHttpResponse response = null;
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nvps));
            httpPost.setHeader("Cookie", cookieConfig.getCookie());
            response = httpclient.execute(httpPost);

            int status = response.getStatusLine().getStatusCode();
            if (status >= SUCCESS && status < MULTIPLE_CHOICES) {
                HttpEntity entity = response.getEntity();
                return entity != null ? EntityUtils.toString(entity) : null;
            } else {
                throw new ClientProtocolException("不可处理的状态码" + status);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public String postJson(String url, JSONObject param) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);

        StringEntity se = new StringEntity(param.toString(), "utf-8");
        httpPost.setEntity(se);
        httpPost.setHeader("Content-Type", "application/json");
        httpPost.setHeader("Cookie", cookieConfig.getCookie());
        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(httpPost);
            int status = response.getStatusLine().getStatusCode();
            if (status >= SUCCESS && status < MULTIPLE_CHOICES) {
                HttpEntity entity = response.getEntity();
                return entity != null ? EntityUtils.toString(entity) : null;
            } else {
                throw new ClientProtocolException("不可处理的状态码" + status);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != response) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
