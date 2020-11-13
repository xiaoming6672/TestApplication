package com.zhang.my.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

/**
 * @author ZhangXiaoMing 2020-08-26 11:52 星期三
 */
public class BaseActivity extends AppCompatActivity {
    protected final String TAG = getClass().getSimpleName();

    protected Activity getActivity() {
        return this;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreate()");
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onPostCreate()");
        super.onPostCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        Log.d(TAG, "onStart()");
        super.onStart();
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "onStop()");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy()");
        super.onDestroy();
    }

    @Override
    public void onContentChanged() {
        Log.d(TAG, "onContentChanged()");
        super.onContentChanged();
    }

    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        Log.d(TAG, "onMenuOpened()");
        return super.onMenuOpened(featureId, menu);
    }

    @Override
    public void onPanelClosed(int featureId, @NonNull Menu menu) {
        Log.d(TAG, "onPanelClosed()");
        super.onPanelClosed(featureId, menu);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        Log.d(TAG, "onSaveInstanceState()");
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Log.d(TAG, "onActivityResult()>>>requestCode = " + requestCode + "  resultCode = " + resultCode);
        super.onActivityResult(requestCode, resultCode, data);
    }

//    @Nullable
//    @Override
//    public View onCreateView(@Nullable View parent, @NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
//        Log.d(TAG, "onCreateView()>>>parent = " + parent + "  name = " + name + "  context = " + context.toString());
//        return super.onCreateView(parent, name, context, attrs);
//    }
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
//        Log.d(TAG, "onCreateView()>>>name = " + name + "  context = " + context.toString());
//        return super.onCreateView(name, context, attrs);
//    }

    @Override
    public void onLowMemory() {
        Log.d(TAG, "onLowMemory()");
        super.onLowMemory();
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "onPause()");
        super.onPause();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        Log.d(TAG, "onNewIntent()");
        super.onNewIntent(intent);
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume()");
        super.onResume();
    }

    @Override
    protected void onResumeFragments() {
        Log.d(TAG, "onResumeFragments()");
        super.onResumeFragments();
    }

    @Override
    public void onAttachFragment(@NonNull Fragment fragment) {
        Log.d(TAG, "onAttachFragment()");
        super.onAttachFragment(fragment);
    }

    @Override
    public void onBackPressed() {
        Log.d(TAG, "onBackPressed()");
        super.onBackPressed();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        Log.d(TAG, "onCreate(savedInstanceState,persistentState)");
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    protected void onRestart() {
        Log.d(TAG, "onRestart()");
        super.onRestart();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        Log.d(TAG, "onSaveInstanceState()");
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Nullable
    @Override
    public CharSequence onCreateDescription() {
        Log.d(TAG, "onCreateDescription()");
        return super.onCreateDescription();
    }

    @Override
    public boolean onKeyMultiple(int keyCode, int repeatCount, KeyEvent event) {
        Log.d(TAG, "onKeyMultiple()>>>keyCode = " + keyCode + "  repeatCount = " + repeatCount);
        return super.onKeyMultiple(keyCode, repeatCount, event);
    }

    @Override
    public boolean onKeyShortcut(int keyCode, KeyEvent event) {
        Log.d(TAG, "onKeyShortcut()>>>keyCode = " + keyCode);
        return super.onKeyShortcut(keyCode, event);
    }

    @Override
    public void onAttachedToWindow() {
        Log.d(TAG, "onAttachedToWindow()");
        super.onAttachedToWindow();
    }

    @Override
    public void onDetachedFromWindow() {
        Log.d(TAG, "onDetachedFromWindow()");
        super.onDetachedFromWindow();
    }
}
