package com.bonc.staff.util;


import com.bonc.staff.map.pojo.MapPoint;
import com.bonc.staff.map.pojo.SpiderRawDto;
import com.bonc.staff.map.service.HttpService;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * 地图工具类
 * 
 * @author Weseley
 *
 */
@Slf4j
public class GisUtil {

    private static final String QQ_GEO_URL = "http://apis.map.qq.com/ws/geocoder/v1/?key=FAKBZ-66B3K-B73JK-AV5T3-HIHJQ-5LB6Y&address=";
    private static final String QQ_TO_BD_URL = "http://apis.map.qq.com/ws/coord/v1/translate?type=3&key=FAKBZ-66B3K-B73JK-AV5T3-HIHJQ-5LB6Y&locations=";
    
    /**
     * 高德地图坐标转换
     */
    private static final String BD_TO_AMAP_URL = "http://restapi.amap.com/v3/assistant/coordinate/convert?key=e304465051eda2075542336a822f6c24&coordsys=baidu&locations=";
    
    /**
     * 高德地图小区接口
     */
//    private static final String AMAP_COMMUNITY_URL = "https://vdata.amap.com/limg?font=small&scl=2&lv=16&t=";
//    private static final String AMAP_COMMUNITY_QUERY_URL = "https://gaode.com/service/poiTipslite?&city=110000&geoobj=116.298521%7C39.949916%7C116.302786%7C39.951233&words=";
    private static final String AMAP_COMMUNITY_QUERY_URL = "http://restapi.amap.com/v3/geocode/regeo?key=e304465051eda2075542336a822f6c24&poitype=住宅小区&extensions=all&batch=false&roadlevel=0&radius=300&location=";

    private static final String AMAP_COMMUNITY_DETAIL_URL = "https://ditu.amap.com/detail/get/detail?id=";
//    private static final String AMAP_COMMUNITY_DETAIL_URL = "https://gaode.com/service/poiInfo?query_type=IDQ&id=";

    private static final String AMAP_TO_BD_URL = "http://api.map.baidu.com/geoconv/v1/?from=3&to=5&ak=2fTYKH2zmCIbSguDjlIFuMsT1pG4c5XW&coords=";
    
    public static final double EA_KM = 6378.245000000;
    public static final double EB_KM = 6356.863018773;

    public static final double PRECISION = 1000000;

    private static final int MAX_BUFFER_PTNS = 65535;

    /**
     * 坐标系
     */
    //private static final SeCoordinateReference CREF = generateSeCoordinateReference();
    /**
     * 个数
     */
    private static final int NUM_PARTS = 1;
    /**
     * 部分
     */
    private static final int[] PART_OFFSETS = new int[] { 0 };

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(GisUtil.class);

    /**
     * 最大处理数
     */
    private static final int MAX_TASK_NUMBER = 50;

    private GisUtil() {

    }


    /**
     * 点在区域内
     * 
     * @param point
     * @param polygon
     */
    //public static boolean isPointInPolygon(MapPoint point, MapPolygon polygon) {
    //    try {
    //        SeShape pointShape = new SeShape(CREF);
    //        SeShape polygonShape = new SeShape(CREF);
    //
    //        pointShape.generatePoint(1, convertToSDEPoints(point));// 生成点
    //        List<MapPoint> points = polygon.getPoints();
    //        // 构造多边形对象
    //        polygonShape.generatePolygon(points.size(), NUM_PARTS, PART_OFFSETS,
    //                convertToSDEPoints(points.toArray(new MapPoint[0])));// 生成多边形
    //
    //        return pointShape.isWithin(polygonShape) || pointShape.isTouching(polygonShape);
    //    } catch (Exception e) {
    //        LOGGER.error("GIS处理检查点在区域内发生异常：", e);
    //        return false;
    //    }
    //}


    /**
     * 地理编码
     * 
     * @param address
     * @return
     * @throws IOException
     */
    public static MapPoint convertGeocode(String address) throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        map.put("isSuccess", "n");
        if (StringUtils.isNotEmpty(address)) {
            String addressParameter = java.net.URLDecoder.decode(address, "UTF-8");
            String url = QQ_GEO_URL + addressParameter;
            // System.out.println(url);
            // {"status":0,"result":{"location":{"lng":123.51581801658569,"lat":41.82350391731906},"precise":0,"confidence":60,"level":"地产小区"}}

            // String result = com.map.util.HttpRequestUtil.sendRequest(url, "GET",
            // "application/json", "");
            HttpService httpService = new HttpService();
            SpiderRawDto resultDTO = httpService.get(url, null);

            String result = resultDTO.getRawContent();
            // 返回的json数据进行转换取值
            Gson gson = new Gson();
            JsonParser parser = new JsonParser();
            JsonObject jsonObject = parser.parse(result).getAsJsonObject();
            if ("0".equalsIgnoreCase(jsonObject.get("status").toString())) {
                LOGGER.info("地址转换腾讯坐标成功！");
                jsonObject = (JsonObject) jsonObject.get("result");
                map = gson.fromJson(jsonObject.get("location"), new TypeToken<Map<String, String>>() {
                }.getType());
                String lng = map.get("lng").toString();
                String lat = map.get("lat").toString();
                String qqToBdURL = QQ_TO_BD_URL + lat + "," + lng;
                // System.out.println(qqToBdURL);
                String bdResult = sendRequest(qqToBdURL, "GET", "application/json", "");

                // 返回的json数据进行转换取值
                JsonObject bdJson = parser.parse(bdResult).getAsJsonObject();
                if ("0".equalsIgnoreCase(bdJson.get("status").toString())) {
                    LOGGER.info("腾讯坐标转换百度坐标成功！");
                    JsonArray jsonArray = (JsonArray) bdJson.get("locations");
                    JsonObject lngAndLat = (JsonObject) jsonArray.get(0);
                    map.put("lng", lngAndLat.get("lng").toString());
                    map.put("lat", lngAndLat.get("lat").toString());
                    MapPoint point = new MapPoint(Double.parseDouble(lngAndLat.get("lng").toString()),
                            Double.parseDouble(lngAndLat.get("lat").toString()));
                    return point;
                }
                map.put("isSuccess", "y");
            } else if("347".equalsIgnoreCase(jsonObject.get("status").toString())) {
                LOGGER.info(jsonObject.toString());
            } else {
                throw new Exception();
            }
        }
        return null;
    }

    public static String sendRequest(String strUrl,String requestMethod,String contentype,String content) throws IOException
    {
        StringBuffer buffer = new StringBuffer();
        URL url = new URL(strUrl);
        HttpURLConnection httpUrlConnection = (HttpURLConnection)url.openConnection();
        httpUrlConnection.setRequestMethod(requestMethod);
        //    	if (content.length() > 0 )
        httpUrlConnection.setRequestProperty("Content-type","application/json;charset=utf-8");
        httpUrlConnection.setDoOutput(true);
        httpUrlConnection.setDoInput(true);
        httpUrlConnection.setUseCaches(false);
        httpUrlConnection.connect();

        if(content.length()>0){
            OutputStreamWriter out = new OutputStreamWriter(httpUrlConnection.getOutputStream(), "UTF-8");
            out.write(content);
            out.flush();
            out.close();
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(httpUrlConnection.getInputStream(),"UTF-8"));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            buffer.append(inputLine.trim());
        }
        in.close();
        httpUrlConnection.disconnect();
        return buffer.toString();
    }
}
