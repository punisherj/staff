package com.bonc.staff.map.pojo;

/**
 * 点(百度坐标系)
 * 
 * @author Weseley
 *
 */
public class MapPoint {

    private double longitude;
    private double latitude;

    /**
     * 构造点
     */
    public MapPoint() {
    }

    /**
     * 构造点
     * 
     * @param longitude
     * @param latitude
     */
    public MapPoint(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    /**
     * 获取经度
     * 
     * @return
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * 设置经度
     * 
     * @param longitude
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * 获取纬度
     * 
     * @return
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * 设置纬度
     * 
     * @param latitude
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

}
