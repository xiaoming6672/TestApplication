package com.zhang.practice.kotlin

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.util.Log

/**
 *
 * @author ZhangXiaoMing
 * 2023-05-09 16:31 周二
 */
class ActivityLifecycleCallbackImpl : Application.ActivityLifecycleCallbacks {

    private val TAG = "ActivityLifecycle"

    /**
     * Called when the Activity calls [super.onCreate()][Activity.onCreate].
     */
    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        Log.d(TAG, "onActivityCreated() ${activity.localClassName}")
    }

    /**
     * Called when the Activity calls [super.onStart()][Activity.onStart].
     */
    override fun onActivityStarted(activity: Activity) {
        Log.d(TAG, "onActivityStarted() ${activity.localClassName}")
    }

    /**
     * Called when the Activity calls [super.onResume()][Activity.onResume].
     */
    override fun onActivityResumed(activity: Activity) {
        Log.d(TAG, "onActivityResumed() ${activity.localClassName}")
    }

    /**
     * Called when the Activity calls [super.onPause()][Activity.onPause].
     */
    override fun onActivityPaused(activity: Activity) {
        Log.d(TAG, "onActivityPaused() ${activity.localClassName}")
    }

    /**
     * Called when the Activity calls [super.onStop()][Activity.onStop].
     */
    override fun onActivityStopped(activity: Activity) {
        Log.d(TAG, "onActivityStopped() ${activity.localClassName}")
    }

    /**
     * Called when the Activity calls
     * [super.onSaveInstanceState()][Activity.onSaveInstanceState].
     */
    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
        Log.d(TAG, "onActivitySaveInstanceState() ${activity.localClassName}")
    }

    /**
     * Called when the Activity calls [super.onDestroy()][Activity.onDestroy].
     */
    override fun onActivityDestroyed(activity: Activity) {
        Log.d(TAG, "onActivityDestroyed() ${activity.localClassName}")
    }

}