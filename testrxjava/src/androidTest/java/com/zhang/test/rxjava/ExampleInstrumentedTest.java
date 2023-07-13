package com.zhang.test.rxjava;

import android.content.Context;
import android.util.Log;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Random;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

import static org.junit.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.zhang.test.rxjava", appContext.getPackageName());
    }

    @Test
    public void rxFlatMap() {
        Random random = new Random();

        float first = 1.5F;
        float second = 2.5F;
        float third = 3.5F;

        Disposable subscribe = Observable.just(first, second, third)
                .flatMap(new Function<Float, ObservableSource<Integer>>() {
                    @Override
                    public ObservableSource<Integer> apply(Float aFloat) throws Throwable {
                        int value = aFloat.intValue();
                        System.out.println("aFloat=" + aFloat + ">>>" + value);
                        return Observable.just(value);
                    }
                })
                .flatMap(new Function<Integer, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(Integer integer) throws Throwable {
                        String value = "[" + integer + "]";
                        System.out.println("integer=" + integer + ">>>" + value);
                        return Observable.just(value);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Throwable {
                        System.out.println("accept>>>s=" + s);
                    }
                });
    }

    @Test
    public void rxFlatMapFromBoolean() {
        boolean result = new Random().nextBoolean();
        Log.i("ZHANG", "result=" + result);

        Disposable subscribe = Observable.just(result)
                .flatMap(new Function<Boolean, ObservableSource<Integer>>() {
                    @Override
                    public ObservableSource<Integer> apply(Boolean aBoolean) throws Throwable {
                        if (aBoolean)
                            return Observable.just(1);
                        else return Observable.just("0")
                                .flatMap(new Function<String, ObservableSource<Integer>>() {
                                    @Override
                                    public ObservableSource<Integer> apply(String s) throws Throwable {
                                        Log.w("ZHANG", "flatMap>>>s=" + s);
                                        return Observable.just(0);
                                    }
                                });
                    }
                })
                .flatMap(new Function<Integer, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(Integer integer) throws Throwable {
                        Log.e("ZHANG", "flatMap>>>integer=" + integer);
                        return Observable.just("A");
                    }
                })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Throwable {
                        System.out.println("accept>>>s=" + s);
                        Log.d("ZHANG", "accept>>>s=" + s);
                    }
                });
    }

}