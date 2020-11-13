package com.zhang.my.test.animation.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.ScaleAnimation;

import com.zhang.my.test.animation.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ScaleAnimation scale = new ScaleAnimation(1F, 1F, 0.3F, 1F,
                Animation.RELATIVE_TO_SELF, 0.5F, Animation.RELATIVE_TO_SELF, 1F);
        scale.setInterpolator(new AccelerateInterpolator());
        scale.setDuration(700);
        scale.setRepeatCount(Animation.INFINITE);
        scale.setRepeatMode(Animation.REVERSE);

        findViewById(R.id.iv_image).startAnimation(scale);

        findViewById(R.id.iv_image).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri packageUri = Uri.parse("package:cn.huidukeji.beautyqueen");

                Intent intent = new Intent(Intent.ACTION_DELETE, packageUri);
                startActivity(intent);

                startActivity(new Intent(MainActivity.this, SecondActivity.class));

//                Calendar calendar = Calendar.getInstance();
//                calendar.roll(Calendar.DAY_OF_MONTH, 3);
//
//                CalendarEventUtils.addCalendarEvent(getActivity(),
//                        "title",
//                        "description",
//                        calendar.getTime().getTime(),
//                        1);
            }
        });


        View iv_accelerate = findViewById(R.id.iv_accelerate);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.translate_home_charm);
        animation.setInterpolator(new LinearInterpolator());
        iv_accelerate.startAnimation(animation);

//        TestFragment fragment = new TestFragment();
//        fragment.setArguments(new Bundle());
//
//        getSupportFragmentManager()
//                .beginTransaction()
//                .add(R.id.fl_container, fragment, fragment.getClass().getName())
//                .commit();

        View iv_red = findViewById(R.id.iv_red);
        Animation redAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.translate_red_envelope);
        redAnim.setInterpolator(new LinearInterpolator());
        iv_red.startAnimation(redAnim);
    }
}