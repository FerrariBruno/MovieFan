package com.xmartlabs.moviefan.controller;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;

import io.reactivex.SingleTransformer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by bruno on 12/12/17.
 */
public class BaseController {
  @NonNull
  private final SingleTransformer singleIoTransformer = upstream -> upstream
      .subscribeOn(Schedulers.io())
      .observeOn(Schedulers.io());

  @CheckResult
  @NonNull
  protected <T> SingleTransformer<T, T> applySingleIoSchedulers() {
    //noinspection unchecked
    return (SingleTransformer<T, T>) singleIoTransformer;
  }
}
