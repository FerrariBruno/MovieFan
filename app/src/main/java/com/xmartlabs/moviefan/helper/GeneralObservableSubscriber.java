package com.xmartlabs.moviefan.helper;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;

import com.xmartlabs.moviefan.ui.common.MovieFanView;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.lang.ref.WeakReference;

public class GeneralObservableSubscriber<T> implements Subscriber<T> {
  @NonNull
  private WeakReference<MovieFanView> viewReference;
  
  public GeneralObservableSubscriber() {
    this(null);
  }

  public GeneralObservableSubscriber(@Nullable MovieFanView movieFanView) {
    viewReference = new WeakReference<>(movieFanView);
  }

  @Override
  public void onSubscribe(Subscription subscription) {}

  @Override
  public void onNext(T t) {}

  @Override
  public void onError(Throwable throwable) {
    MovieFanView view = viewReference.get();
    if (alertOnError(throwable) && view != null && view.isViewAlive()) {
      view.showError(throwable, getErrorMessage(throwable));
    }
  }

  @Override
  public void onComplete() {}

  @Nullable
  @StringRes
  protected Integer getErrorMessage(Throwable throwable) {
    return null;
  }

  @CheckResult
  protected boolean alertOnError(Throwable throwable) {
    return true;
  }
}
