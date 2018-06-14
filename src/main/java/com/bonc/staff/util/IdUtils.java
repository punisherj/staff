package com.bonc.staff.util;

import java.util.UUID;

/**
 * @author xukj
 */
public class IdUtils {
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static void main(String[] args) {
        System.out.println(IdUtils.getUUID());
        System.out.println(String.valueOf(IdUtils.getUUID()).length());
    }
}
