package com.xmartlabs.moviefan.ui.common;

import android.support.annotation.StringRes;

import com.xmartlabs.bigbang.ui.mvp.MvpView;

import io.reactivex.CompletableTransformer;
import io.reactivex.FlowableTransformer;
import io.reactivex.MaybeTransformer;
import io.reactivex.ObservableTransformer;
import io.reactivex.SingleTransformer;
import io.reactivex.annotations.NonNull;

public interface MovieFanView extends MvpView {
  boolean isViewAlive();
  @NonNull
  CompletableTransformer keepAliveWhileVisibleCompletable();
  @NonNull
  <T> FlowableTransformer<T, T> keepAliveWhileVisibleFlowable();
  @NonNull
  <T> MaybeTransformer<T, T> keepAliveWhileVisibleMaybe();
  @NonNull
  <T> ObservableTransformer<T, T> keepAliveWhileVisibleObservable();
  @NonNull
  <T> SingleTransformer<T, T> keepAliveWhileVisibleSingle();
  void showError(@StringRes int message);
  void showError(@StringRes int message, @StringRes int title);
  void showError(@StringRes int message, @StringRes int title, @StringRes int buttonTitle);
  void showError(@NonNull Throwable error, @StringRes Integer message);
}
