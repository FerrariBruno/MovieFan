package com.xmartlabs.moviefan.ui.common;

import android.support.annotation.StringRes;

import com.xmartlabs.bigbang.ui.mvp.MvpView;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.SingleTransformer;
import io.reactivex.annotations.NonNull;

public interface MovieFanView extends MvpView {
  boolean isViewAlive();
  @NonNull
  Completable keepAliveWhileVisible(@NonNull Completable source);
  @NonNull
  <T> Flowable<T> keepAliveWhileVisible(@NonNull Flowable<T> source);
  @NonNull
  <T> Maybe<T> keepAliveWhileVisible(@NonNull Maybe<T> source);
  @NonNull
  <T> Observable<T> keepAliveWhileVisible(@NonNull Observable<T> source);
  @NonNull
  <T> SingleTransformer<T, T> keepAliveWhileVisible();
  void showError(@StringRes int message);
  void showError(@StringRes int message, @StringRes int title);
  void showError(@StringRes int message, @StringRes int title, @StringRes int buttonTitle);
  void showError(@NonNull Throwable error, @StringRes Integer message);
}
