package com.zhang.my;

import android.util.Log;

import org.junit.Test;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import static org.junit.Assert.*;

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
        Date date = new Date();

        Calendar calendar1 = Calendar.getInstance();
        Calendar now = Calendar.getInstance();

        String yesterday = "2020-07-21 09:44:30";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.getDefault());
        Date yesterdayDate = sdf.parse(yesterday);

//        calendar1.setTime(date);
        calendar1.setTime(yesterdayDate);
        now.setTime(date);

//        calendar1.add(Calendar.SECOND, -10);

        int result = calendar1.compareTo(now);

        System.out.println("compare result = " + result);

        long time1 = calendar1.getTime().getTime();
        long time2 = now.getTime().getTime();

        long timeDiff = time2 - time1;
        System.out.println("timeDiff = " + timeDiff);
    }

    @Test
    public void testRandom() {
        Random random = new Random();

        System.out.println(random.nextInt());
        System.out.println(random.nextInt());
        System.out.println(random.nextInt());
        System.out.println(random.nextInt());
        System.out.println(random.nextInt());

        System.out.println(random.nextInt(2));
        System.out.println(random.nextInt(20));
        System.out.println(random.nextInt(100));
    }

    @Test
    public void testFile() {
        String path = "";
        new File(path);
    }
}