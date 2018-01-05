package com.xmartlabs.moviefan.ui.common;

import android.support.annotation.StringRes;

import com.afollestad.materialdialogs.MaterialDialog;
import com.trello.rxlifecycle2.RxLifecycle;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.xmartlabs.bigbang.ui.mvp.BaseMvpFragment;
import com.xmartlabs.moviefan.R;

import java.io.IOException;
import java.util.concurrent.CancellationException;

import io.reactivex.CompletableTransformer;
import io.reactivex.FlowableTransformer;
import io.reactivex.MaybeTransformer;
import io.reactivex.ObservableTransformer;
import io.reactivex.SingleTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.exceptions.CompositeException;

public abstract class MovieFanFragment<V extends MovieFanView, P extends MovieFanPresenter<V>>
    extends BaseMvpFragment<V, P> implements MovieFanView {
  @Override
  public boolean isViewAlive() {
    return isAdded() && getActivity() != null;
  }

  @NonNull
  @Override
  public CompletableTransformer keepAliveWhileVisibleCompletable() {
    return upstream -> upstream
        .compose(RxLifecycle.bindUntilEvent(lifecycle(), FragmentEvent.DESTROY_VIEW))
        .observeOn(AndroidSchedulers.mainThread());
  }

  @NonNull
  @Override
  public <T> FlowableTransformer<T, T> keepAliveWhileVisibleFlowable() {
    return upstream -> upstream
        .compose(RxLifecycle.bindUntilEvent(lifecycle(), FragmentEvent.DESTROY_VIEW))
        .observeOn(AndroidSchedulers.mainThread());
  }

  @NonNull
  @Override
  public <T> MaybeTransformer<T, T> keepAliveWhileVisibleMaybe() {
    return upstream -> upstream
        .compose(RxLifecycle.bindUntilEvent(lifecycle(), FragmentEvent.DESTROY_VIEW))
        .observeOn(AndroidSchedulers.mainThread());
  }

  @NonNull
  @Override
  public <T> ObservableTransformer<T, T> keepAliveWhileVisibleObservable() {
    return upstream -> upstream
        .compose(RxLifecycle.bindUntilEvent(lifecycle(), FragmentEvent.DESTROY_VIEW))
        .observeOn(AndroidSchedulers.mainThread());
  }

  @NonNull
  @Override
  public <T> SingleTransformer<T, T> keepAliveWhileVisibleSingle() {
    return upstream -> upstream
        .compose(RxLifecycle.bindUntilEvent(lifecycle(), FragmentEvent.DESTROY_VIEW))
        .observeOn(AndroidSchedulers.mainThread());
  }

  @Override
  public void showError(@StringRes int message) {
    showError(message, R.string.error);
  }

  @Override
  public void showError(@StringRes int message, @StringRes int title) {
    showError(message, title, android.R.string.ok);
  }

  @Override
  public void showError(@StringRes int message, @StringRes int title, @StringRes int buttonTitle) {
    if (isViewAlive()) {
      new MaterialDialog.Builder(getContext())
          .title(title)
          .content(message)
          .positiveText(buttonTitle)
          .build()
          .show();
    }
  }

  @Override
  public void showError(@NonNull Throwable error, @StringRes Integer message) {
    if (error instanceof CompositeException) {
      error = ((CompositeException) error).getExceptions().get(0);
    }
    if (error instanceof CancellationException) {
      return;
    }
    if (error instanceof IOException) {
      showError(R.string.check_your_internet_connection, R.string.no_internet_connection);
    } else if (message == null) {
      showError(R.string.error_service_call_generic);
    } else {
      showError(message);
    }
  }
}
