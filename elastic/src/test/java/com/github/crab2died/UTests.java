package com.github.crab2died;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class UTests {

    @Test
    public void timeTest() {

        Date date = new Date(1533531180000L);
        SimpleDateFormat bjSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        bjSdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        System.out.println(bjSdf.format(date));
    }
}
