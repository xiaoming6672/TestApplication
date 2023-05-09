package com.zhang.practice.kotlin

import android.app.Activity
import android.app.Application
import android.os.Bundle

/**
 *
 * @author ZhangXiaoMing
 * 2023-05-09 16:27 周二
 */
class KotlinApplication : Application() {

    /**
     * Called when the application is starting, before any activity, service,
     * or receiver objects (excluding content providers) have been created.
     *
     *
     * Implementations should be as quick as possible (for example using
     * lazy initialization of state) since the time spent in this function
     * directly impacts the performance of starting the first activity,
     * service, or receiver in a process.
     *
     *
     * If you override this method, be sure to call `super.onCreate()`.
     *
     *
     * Be aware that direct boot may also affect callback order on
     * Android [android.os.Build.VERSION_CODES.N] and later devices.
     * Until the user unlocks the device, only direct boot aware components are
     * allowed to run. You should consider that all direct boot unaware
     * components, including such [android.content.ContentProvider], are
     * disabled until user unlock happens, especially when component callback
     * order matters.
     */
    override fun onCreate() {
        super.onCreate()

        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
            /**
             * Called when the Activity calls [super.onCreate()][Activity.onCreate].
             */
            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                TODO("Not yet implemented")
            }

            /**
             * Called when the Activity calls [super.onStart()][Activity.onStart].
             */
            override fun onActivityStarted(activity: Activity) {
                TODO("Not yet implemented")
            }

            /**
             * Called when the Activity calls [super.onResume()][Activity.onResume].
             */
            override fun onActivityResumed(activity: Activity) {
                TODO("Not yet implemented")
            }

            /**
             * Called when the Activity calls [super.onPause()][Activity.onPause].
             */
            override fun onActivityPaused(activity: Activity) {
                TODO("Not yet implemented")
            }

            /**
             * Called when the Activity calls [super.onStop()][Activity.onStop].
             */
            override fun onActivityStopped(activity: Activity) {
                TODO("Not yet implemented")
            }

            /**
             * Called when the Activity calls
             * [super.onSaveInstanceState()][Activity.onSaveInstanceState].
             */
            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
                TODO("Not yet implemented")
            }

            /**
             * Called when the Activity calls [super.onDestroy()][Activity.onDestroy].
             */
            override fun onActivityDestroyed(activity: Activity) {
                TODO("Not yet implemented")
            }
        })
        registerActivityLifecycleCallbacks(ActivityLifecycleCallbackImpl())
    }
}