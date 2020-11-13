package com.zhang.my.activity;

import android.os.Bundle;

import com.zhang.my.R;
import com.zhang.my.fragment.TestFragment;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TestFragment fragment = new TestFragment();
        fragment.setArguments(new Bundle());

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fl_container, fragment, fragment.getClass().getName())
                .commit();
    }
}
