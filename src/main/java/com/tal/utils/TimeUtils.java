package com.tal.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {
    public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");

    public static String getCurrentFormatTime(){
        return sdf.format(new Date());
    }
}
