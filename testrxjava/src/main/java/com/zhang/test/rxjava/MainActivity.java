package com.zhang.test.rxjava;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "RxMainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ObservableOnSubscribe<String> source = new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Throwable {
                emitter.onNext("A");
                emitter.onNext("B");
                emitter.onNext("C");
                emitter.onComplete();
            }
        };
        Observable.fromArray(1, 2, 3, 4)
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Throwable {
                        return integer % 2 == 0;
                    }
                })
                .map(new Function<Integer, String>() {
                    @Override
                    public String apply(Integer integer) throws Throwable {
                        return "0x" + integer;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.d(TAG, "onSubscribe()");
                    }

                    @Override
                    public void onNext(@NonNull String integer) {
                        Log.i(TAG, "onNext: integer = " + integer);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG, "onError: e = " + e.toString());
                    }

                    @Override
                    public void onComplete() {
                        Log.i(TAG, "onComplete()");
                    }
                });

//        Observable.create(source)
//        Observable<String> observable = Observable.fromArray("01", "02", "03A")
////                .flatMap(new Function<String, ObservableSource<String>>() {
////                    @Override
////                    public ObservableSource<String> apply(String s) throws Throwable {
////                        return null;
////                    }
////                })
//                .filter(new Predicate<String>() {
//                    /**
//                     * Test the given input value and return a
//                     * boolean.
//                     *
//                     * @param s the value
//                     *
//                     * @return the boolean result
//                     * @throws Throwable if the implementation
//                     *                   wishes to throw any type of
//                     *                   exception
//                     */
//                    @Override
//                    public boolean test(String s) throws Throwable {
//                        return !s.contains("A");
//                    }
//                })
//                .map(new Function<String, String>() {
//                    @Override
//                    public String apply(String s) throws Throwable {
//                        return s + "_123";
//                    }
//                })
//                .observeOn(AndroidSchedulers.mainThread());
//        observable.subscribe(new Observer<String>() {
//            @Override
//            public void onSubscribe(@NonNull Disposable d) {
//                Log.d(TAG, "onSubscribe()");
//            }
//
//            @Override
//            public void onNext(@NonNull String s) {
//                Log.i(TAG, "onNext: s = " + s);
//            }
//
//            @Override
//            public void onError(@NonNull Throwable e) {
//                Log.e(TAG, "onError: e = " + e.toString());
//            }
//
//            @Override
//            public void onComplete() {
//                Log.i(TAG, "onComplete()");
//            }
//        });
    }
}