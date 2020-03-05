package com.coffeit.organizze.helper;

import java.text.SimpleDateFormat;

public class DateCustom {

    public static String actualDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return simpleDateFormat.format(System.currentTimeMillis());
    }

    public static String formatDateMY(String date) {
        String[] res = date.split("/");
        String month = res[1];
        String year = res[2];
        return year + month;
    }
}
