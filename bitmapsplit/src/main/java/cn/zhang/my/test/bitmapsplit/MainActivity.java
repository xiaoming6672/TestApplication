package cn.zhang.my.test.bitmapsplit;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        ImageView iv1 = findViewById(R.id.iv_part_1);
        ImageView iv2 = findViewById(R.id.iv_part_2);
        ImageView iv3 = findViewById(R.id.iv_part_3);
        ImageView iv4 = findViewById(R.id.iv_part_4);
        ImageView iv5 = findViewById(R.id.iv_part_5);
        ImageView iv6 = findViewById(R.id.iv_part_6);
        ImageView iv7 = findViewById(R.id.iv_part_7);
        ImageView iv8 = findViewById(R.id.iv_part_8);
        ImageView iv9 = findViewById(R.id.iv_part_9);
        ImageView iv10 = findViewById(R.id.iv_part_10);
        ImageView iv11 = findViewById(R.id.iv_part_11);
        ImageView iv12 = findViewById(R.id.iv_part_12);

        Bitmap source = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_test_2);

        int width = source.getWidth() / 3;
        int height = source.getHeight() / 4;

        Bitmap bmp1 = Bitmap.createBitmap(source, 0, 0, width, height);
        iv3.setImageBitmap(bmp1);

        Bitmap bmp2 = Bitmap.createBitmap(source, width, 0, width, height);
        iv6.setImageBitmap(bmp2);

        Bitmap bmp3 = Bitmap.createBitmap(source, width * 2, 0, width, height);
        iv1.setImageBitmap(bmp3);

        Bitmap bmp4 = Bitmap.createBitmap(source, 0, height, width, height);
        iv5.setImageBitmap(bmp4);

        Bitmap bmp5 = Bitmap.createBitmap(source, width, height, width, height);
        iv4.setImageBitmap(bmp5);

        Bitmap bmp6 = Bitmap.createBitmap(source, width * 2, height, width, height);
        iv2.setImageBitmap(bmp6);

        Bitmap bmp7 = Bitmap.createBitmap(source, 0, height * 2, width, height);
        iv9.setImageBitmap(bmp7);

        Bitmap bmp8 = Bitmap.createBitmap(source, width, height * 2, width, height);
        iv11.setImageBitmap(bmp8);

        Bitmap bmp9 = Bitmap.createBitmap(source, width * 2, height * 2, width, height);
        iv7.setImageBitmap(bmp9);

        Bitmap bmp10 = Bitmap.createBitmap(source, 0, height * 3, width, height);
        iv12.setImageBitmap(bmp10);

        Bitmap bmp11 = Bitmap.createBitmap(source, width, height * 3, width, height);
        iv8.setImageBitmap(bmp11);

        Bitmap bmp12 = Bitmap.createBitmap(source, width * 2, height * 3, width, height);
        iv10.setImageBitmap(bmp12);

    }
}