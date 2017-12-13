package com.xmartlabs.moviefan.controller;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;

import io.reactivex.CompletableTransformer;
import io.reactivex.FlowableTransformer;
import io.reactivex.MaybeTransformer;
import io.reactivex.ObservableTransformer;
import io.reactivex.SingleTransformer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by bruno on 12/12/17.
 */
public class BaseController {
  @NonNull
  private final CompletableTransformer completableIoTransformer = upstream -> upstream
      .subscribeOn(Schedulers.io())
      .observeOn(Schedulers.io());

  @NonNull
  private final FlowableTransformer flowableIoTransformer = upstream -> upstream
      .subscribeOn(Schedulers.io())
      .observeOn(Schedulers.io());
  @NonNull
  private final MaybeTransformer maybeIoTransformer = upstream -> upstream
      .subscribeOn(Schedulers.io())
      .observeOn(Schedulers.io());
  @NonNull
  private final ObservableTransformer observableIoTransformer = observable -> observable
      .subscribeOn(Schedulers.io())
      .observeOn(Schedulers.io());
  @NonNull
  private final SingleTransformer singleIoTransformer = upstream -> upstream
      .subscribeOn(Schedulers.io())
      .observeOn(Schedulers.io());

  @CheckResult
  @NonNull
  protected <T> FlowableTransformer<T, T> applyFlowableIoSchedulers() {
    //noinspection unchecked
    return (FlowableTransformer<T, T>) flowableIoTransformer;
  }

  @CheckResult
  @NonNull
  protected <T> MaybeTransformer<T, T> applyMaybeIoSchedulers() {
    //noinspection unchecked
    return (MaybeTransformer<T, T>) maybeIoTransformer;
  }

  @CheckResult
  @NonNull
  protected <T> ObservableTransformer<T, T> applyObservableIoSchedulers() {
    //noinspection unchecked
    return (ObservableTransformer<T, T>) observableIoTransformer;
  }

  @CheckResult
  @NonNull
  protected <T> SingleTransformer<T, T> applySingleIoSchedulers() {
    //noinspection unchecked
    return (SingleTransformer<T, T>) singleIoTransformer;
  }
}
