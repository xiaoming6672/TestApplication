package com.zhang.my.test.animator.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.zhang.my.test.animator.R;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private ImageView iv_icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv_icon = findViewById(R.id.iv_icon);
        iv_icon.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        testAnimator();
    }

    private void testAnimator() {
        ValueAnimator animator = ValueAnimator.ofFloat(-4F, 4F);
        animator.setDuration(4000);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator value) {
                Log.d(TAG, "value = " + value.getAnimatedValue());
            }
        });
        animator.addListener(new Animator.AnimatorListener() {

            long startTime;

            @Override
            public void onAnimationStart(Animator animation) {
                startTime = System.currentTimeMillis();
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                long endTime = System.currentTimeMillis();
                Log.e(TAG, "time = " + (endTime - startTime));
                Log.w(TAG, "duration = " + animation.getDuration());
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
        animator.start();
    }

}
