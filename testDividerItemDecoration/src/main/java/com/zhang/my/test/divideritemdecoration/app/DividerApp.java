package com.zhang.my.test.divideritemdecoration.app;

import android.app.Application;

import com.zhang.library.utils.LogUtils;
import com.zhang.library.utils.context.ContextUtils;

/**
 * @author ZhangXiaoMing 2020-11-13 11:24 星期五
 */
public class DividerApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.setDebug(true);
        ContextUtils.set(getApplicationContext());
    }
}
