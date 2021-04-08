package com.zhang.library.test.appclone.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.zhang.library.test.appclone.R;
import com.zhang.library.test.appclone.util.AccountUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String profile = AccountUtils.createProfile(this);
    }
}