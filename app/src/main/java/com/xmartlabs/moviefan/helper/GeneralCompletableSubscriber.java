package com.xmartlabs.moviefan.helper;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;

import com.xmartlabs.moviefan.ui.common.MovieFanView;

import java.lang.ref.WeakReference;

import io.reactivex.CompletableObserver;
import io.reactivex.disposables.Disposable;

public class GeneralCompletableSubscriber implements CompletableObserver {
  @NonNull
  private WeakReference<MovieFanView> viewReference;

  public GeneralCompletableSubscriber() {
    this(null);
  }

  public GeneralCompletableSubscriber(@Nullable MovieFanView movieFanView) {
    viewReference = new WeakReference<>(movieFanView);
  }

  @Override
  public void onSubscribe(@NonNull Disposable disposable) {}

  @Override
  public void onComplete() {}

  @Override
  public void onError(@NonNull Throwable throwable) {
    MovieFanView view = viewReference.get();
    if (alertOnError(throwable) && view != null && view.isViewAlive()) {
      view.showError(throwable, getErrorMessage(throwable));
    }
  }

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
