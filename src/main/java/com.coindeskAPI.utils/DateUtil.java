package com.coindeskAPI.utils;

import com.coindeskAPI.enums.DatePatterns;

import java.time.LocalDateTime;


public class DateUtil {

    private DateUtil() {
    }

    /**
     * 取得目前日期(LocalDateTime格式)
     * @return
     */
    public static String getCurrentLocalDateTimeStr(DatePatterns datePatterns){
        return parseToStr(LocalDateTime.now(), datePatterns);
    } 
  
}
