package com.zhang.my.test.animator.activity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.zhang.my.test.animator.R;
import com.zhang.my.test.animator.constant.PropertyName;

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
//        testAnimatorDuration();
        testTranslateAnimator();
    }

    private void testTranslateAnimator() {
        int duration = 1000;

        int width = iv_icon.getWidth();

        ObjectAnimator xAnimator = ObjectAnimator.ofFloat(iv_icon, PropertyName.TRANSLATION_X, 3.35F * width, 0F);
        xAnimator.setDuration(duration);
        xAnimator.setInterpolator(new LinearInterpolator());
//        xAnimator.start();

        int height = iv_icon.getHeight();

        ObjectAnimator yAnimator = ObjectAnimator.ofFloat(iv_icon, PropertyName.TRANSLATION_Y, -2.63F * height, -(2.63f + 1.5F) * height, 0F);
        yAnimator.setDuration(duration);
        yAnimator.setInterpolator(new TimeInterpolator() {
            @Override
            public float getInterpolation(float input) {
                return (float) Math.sin(input);
            }
        });
//        yAnimator.start();

        AnimatorSet set = new AnimatorSet();
        set.playTogether(xAnimator, yAnimator);
        set.start();
    }

    private void testAnimatorDuration() {
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
