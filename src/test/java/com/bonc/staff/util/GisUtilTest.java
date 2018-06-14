package com.bonc.staff.util;


import com.bonc.staff.map.pojo.MapPoint;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class GisUtilTest {

    @Test
    public void convertGeocode() throws Exception {
        MapPoint mp = GisUtil.convertGeocode("北地联通人民路9号");
        if(null != mp) {
            log.info("精度:维度：{},{}",mp.getLongitude(),mp.getLatitude());
        }
        //NumberFormat nt = NumberFormat.getPercentInstance();
        //nt.setMinimumFractionDigits(0);
        //log.info(nt.format((float)200/5747));
    }
}
