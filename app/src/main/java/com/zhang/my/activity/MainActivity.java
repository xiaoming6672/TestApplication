package com.zhang.my.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.zhang.my.R;
import com.zhang.my.fragment.TestFragment;

import java.util.Random;

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

        View view = findViewById(R.id.tv_text);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.getHandler().post(getRunnable());
                v.getHandler().postDelayed(getRunnable(), 3000);
                v.getHandler().postDelayed(getRunnable(), 4000);
                v.getHandler().postDelayed(getRunnable(), 7000);
                v.getHandler().postDelayed(getRunnable(), 7500);
            }
        });
        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                v.getHandler().removeCallbacks(getRunnable());
                return true;
            }
        });
    }

    private Runnable mRunnable;

    public Runnable getRunnable() {
        if (mRunnable == null) {
            mRunnable = new Runnable() {
                @Override
                public void run() {
                    Log.i(MainActivity.class.getSimpleName(), "ZHANG  " + new Random().nextInt(10));
                }
            };
        }
        return mRunnable;
    }
}
