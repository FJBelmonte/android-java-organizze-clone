package com.coffeit.organizze.helper;

import java.text.SimpleDateFormat;

public class DateCustom {

    public static String DateCustom(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return simpleDateFormat.format(System.currentTimeMillis());
    }
}
