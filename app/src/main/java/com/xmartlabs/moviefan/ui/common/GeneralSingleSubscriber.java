package com.xmartlabs.moviefan.ui.common;

import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import timber.log.Timber;

/**
 * Created by bruno on 12/11/17.
 */
public class GeneralSingleSubscriber<T> implements SingleObserver<T> {
  @Override
  public void onSubscribe(@NonNull Disposable disposable) {}

  @CallSuper
  @Override
  public void onError(@NonNull Throwable throwable) {
    Timber.e(throwable);
  }

  @Override
  public void onSuccess(@NonNull T t) {}
}
