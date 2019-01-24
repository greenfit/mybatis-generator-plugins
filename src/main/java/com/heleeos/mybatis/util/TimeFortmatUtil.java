package com.heleeos.mybatis.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by liyu on 2019/1/24.
 */
public class TimeFortmatUtil {

    /**
     * 获取当前时间
     */
    public static String getNow(String pattern) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        return dateTimeFormatter.format(LocalDateTime.now());
    }
}
