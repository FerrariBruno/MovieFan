package com.xmartlabs.moviefan.helper;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;

import com.xmartlabs.moviefan.ui.common.MovieFanView;

import java.lang.ref.WeakReference;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public class GeneralSingleSubscriber<T> implements SingleObserver<T> {
  @NonNull
  private WeakReference<MovieFanView> viewReference;

  public GeneralSingleSubscriber() {
    this(null);
  }

  public GeneralSingleSubscriber(@Nullable MovieFanView movieFanView) {
    viewReference = new WeakReference<>(movieFanView);
  }

  @Override
  public void onSubscribe(@NonNull Disposable disposable) {}

  @Override
  public void onError(@NonNull Throwable throwable) {
    MovieFanView view = viewReference.get();
    if (alertOnError(throwable) && view != null && view.isViewAlive()) {
      view.showError(throwable, getErrorMessage(throwable));
    }
  }

  @Override
  public void onSuccess(@NonNull T t) {}

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
