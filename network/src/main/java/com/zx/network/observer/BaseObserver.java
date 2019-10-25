package com.zx.network.observer;

import com.zx.network.errorhandler.ExceptionHandle;

import io.reactivex.observers.DisposableObserver;

public abstract class BaseObserver<T> extends DisposableObserver<T> {

    @Override
    public void onNext(T t) {
        onSuccess(t);
    }

    @Override
    public void onError(Throwable throwable) {
        ExceptionHandle.handleException(throwable);
        onFailure(ExceptionHandle.handleException(throwable));
    }

    @Override
    public void onComplete() {

    }

    public abstract void onSuccess(T t);

    public abstract void onFailure(ExceptionHandle.ResponeThrowable s);
}
