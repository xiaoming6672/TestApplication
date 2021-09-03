package com.zhang.test.testview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

/**
 * @author ZhangXiaoMing 2021-07-22 16:17 星期四
 */
public class TestPathImageView extends AppCompatImageView {

    public TestPathImageView(@NonNull Context context) {
        super(context);
    }

    public TestPathImageView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TestPathImageView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Drawable drawable = getDrawable();
        if (drawable == null || getWidth() * getHeight() == 0)
            return;

        mBitmap = getBitmapFromDrawable(drawable);
        if (mBitmap == null)
            return;

        if (mBitmap != null) {
            mBitmapShader = new BitmapShader(mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
            mBitmapPaint.setAntiAlias(true);
            mBitmapHeight = mBitmap.getHeight();
            mBitmapWidth = mBitmap.getWidth();
            updateShaderMatrix();
            mBitmapPaint.setShader(mBitmapShader);
        }

        canvas.drawPath(getPath(), mBitmapPaint);

//        super.onDraw(canvas);
    }

    private Path getPath() {
        Path path = new Path();

        int width = getMeasuredWidth();
        int height = getMeasuredHeight();

        path.moveTo(width * 0.1F, height * 0.2F);
        path.lineTo(width * 0.2F, height * 0.1F);
        path.lineTo(width * 0.5F, height * 0.1F);
        path.lineTo(width * 0.55F, height * 0.05f);
        path.lineTo(width * 0.9F, height * 0.05F);
        path.lineTo(width * 0.9F, height * 0.9F);
        path.lineTo(width * 0.1F, height * 0.9F);
        path.lineTo(width * 0.1F, height * 0.2F);

        return path;
    }

    private static final Bitmap.Config BITMAP_CONFIG = Bitmap.Config.ARGB_8888;
    private static final int COLOR_DRAWABLE_DIMENSION = 1;
    private Bitmap mBitmap;

    private int mBitmapWidth;
    private int mBitmapHeight;
    private final Matrix mShaderMatrix = new Matrix();
    private final Paint mBitmapPaint = new Paint();
    private BitmapShader mBitmapShader;

    private Bitmap getBitmapFromDrawable(Drawable drawable) {
        if (drawable == null) {
            return null;
        }
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }

        try {
            Bitmap bitmap;
            if (drawable instanceof ColorDrawable) {
                bitmap = Bitmap.createBitmap(COLOR_DRAWABLE_DIMENSION,
                        COLOR_DRAWABLE_DIMENSION, BITMAP_CONFIG);
            } else {
                bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
                        drawable.getIntrinsicHeight(), BITMAP_CONFIG);
            }

            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
            drawable.draw(canvas);
            return bitmap;
        } catch (OutOfMemoryError e) {
            return null;
        }
    }

    private void updateShaderMatrix() {
        float scale;
        mShaderMatrix.set(null);
        if (mBitmapWidth != mBitmapHeight) {
            scale = Math.max((float) getMeasuredWidth() / mBitmapWidth, (float) getMeasuredHeight() / mBitmapHeight);
        } else {
            scale = (float) getMeasuredWidth() / mBitmapWidth;
        }

        mShaderMatrix.setScale(scale, scale);//放大铺满

        float dx = getMeasuredWidth() - mBitmapWidth * scale;
        float dy = getMeasuredHeight() - mBitmapHeight * scale;
        mShaderMatrix.postTranslate(dx / 2, dy / 2);//平移居中
        mBitmapShader.setLocalMatrix(mShaderMatrix);
    }

}
