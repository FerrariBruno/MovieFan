package com.xmartlabs.moviefan.ui.common;

import android.support.annotation.NonNull;
import android.util.Log;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

/**
 * Created by bruno on 12/11/17.
 */
public class GeneralSingleSubscriber<T> implements SingleObserver<T> {
  @Override
  public void onSubscribe(@NonNull Disposable disposable) {}

  @Override
  public void onError(@NonNull Throwable throwable) {
    Log.d("onError - Subscriber", throwable.getMessage());
  }

  @Override
  public void onSuccess(@NonNull T t) {}
}
