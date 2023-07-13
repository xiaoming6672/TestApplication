package com.zhang.test.rxjava;

import org.junit.Test;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    private void print(Object obj) {
        System.out.println(obj);
    }

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void rxCreate() {
        Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Object> emitter) throws Throwable {

            }
        }).subscribe(new Observer<Object>() {
            /**
             * Provides the {@link Observer} with the means of cancelling
             * (disposing) the connection (channel) with the {@link Observable}
             * in both synchronous (from within {@link #onNext(Object)}) and
             * asynchronous manner.
             *
             * @param d the {@link Disposable} instance whose {@link
             *          Disposable#dispose()} can be called anytime to cancel
             *          the connection
             *
             * @since 2.0
             */
            @Override
            public void onSubscribe(@NonNull Disposable d) {
            }

            /**
             * Provides the {@link Observer} with a new item to observe.
             * <p>
             * The {@link Observable} may call this method 0 or more times.
             * <p>
             * The {@code Observable} will not call this method again after it
             * calls either {@link #onComplete} or {@link #onError}.
             *
             * @param o the item emitted by the Observable
             */
            @Override
            public void onNext(@NonNull Object o) {
            }

            /**
             * Notifies the {@link Observer} that the {@link Observable} has
             * experienced an error condition.
             * <p>
             * If the {@code Observable} calls this method, it will not
             * thereafter call {@link #onNext} or {@link #onComplete}.
             *
             * @param e the exception encountered by the Observable
             */
            @Override
            public void onError(@NonNull Throwable e) {
            }

            /**
             * Notifies the {@link Observer} that the {@link Observable} has
             * finished sending push-based notifications.
             * <p>
             * The {@code Observable} will not call this method if it calls
             * {@link #onError}.
             */
            @Override
            public void onComplete() {
            }
        });
    }

}