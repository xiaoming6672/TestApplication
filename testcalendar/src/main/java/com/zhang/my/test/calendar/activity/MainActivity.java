package com.zhang.my.test.calendar.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.zhang.my.test.calendar.R;
import com.zhang.my.test.calendar.utils.CalendarEventUtils;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Calendar calendar = Calendar.getInstance();
        calendar.roll(Calendar.DAY_OF_MONTH, 3);

        CalendarEventUtils.addCalendarEvent(this,
                "title",
                "description",
                calendar.getTime().getTime(),
                1);
    }
}
