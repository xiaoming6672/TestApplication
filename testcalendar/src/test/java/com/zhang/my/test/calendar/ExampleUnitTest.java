package com.zhang.my.test.calendar;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void compareCalendar() throws ParseException {
        Date now = new Date();

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date before = format.parse("2021-03-01 13:00:00");

        System.out.println("now = " + now.toString());
        System.out.println("before = " + before.toString());

        System.out.println("now.compareTo(before) = " + now.compareTo(before));

        float day = 1F * (now.getTime() - before.getTime()) / (24 * 60 * 60 * 1000);
        System.out.println("day = " + day);

        BigDecimal dayDiff = new BigDecimal(day);
        System.out.println("dayDiff = " + dayDiff.setScale(0, RoundingMode.UP));
    }
}