package com.coffeit.organizze.helper;

import java.text.SimpleDateFormat;

public class DateCustom {

    public static String actualDate(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return simpleDateFormat.format(System.currentTimeMillis());
    }
}
