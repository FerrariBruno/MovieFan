package com.xmartlabs.moviefan.ui.common;

import android.support.annotation.StringRes;

import com.afollestad.materialdialogs.MaterialDialog;
import com.trello.rxlifecycle2.RxLifecycle;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.xmartlabs.bigbang.ui.mvp.BaseMvpFragment;
import com.xmartlabs.moviefan.R;

import java.io.IOException;
import java.util.concurrent.CancellationException;

import io.reactivex.Completable;
import io.reactivex.CompletableTransformer;
import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.Maybe;
import io.reactivex.MaybeTransformer;
import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.SingleTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.exceptions.CompositeException;

public abstract class MovieFanFragment<V extends MovieFanView, P extends MovieFanPresenter<V>>
    extends BaseMvpFragment<V, P> implements MovieFanView {
  @NonNull
  private final CompletableTransformer completableTransformer = upstream -> upstream
      .compose(RxLifecycle.bindUntilEvent(lifecycle(), FragmentEvent.DESTROY_VIEW))
      .observeOn(AndroidSchedulers.mainThread());
  @NonNull
  private final FlowableTransformer flowableTransformer = upstream -> upstream
      .compose(RxLifecycle.bindUntilEvent(lifecycle(), FragmentEvent.DESTROY_VIEW))
      .observeOn(AndroidSchedulers.mainThread());
  @NonNull
  private final MaybeTransformer maybeTransformer = upstream -> upstream
      .compose(RxLifecycle.bindUntilEvent(lifecycle(), FragmentEvent.DESTROY_VIEW))
      .observeOn(AndroidSchedulers.mainThread());
  @NonNull
  private final ObservableTransformer observableTransformer = upstream -> upstream
      .compose(RxLifecycle.bindUntilEvent(lifecycle(), FragmentEvent.DESTROY_VIEW))
      .observeOn(AndroidSchedulers.mainThread());
  @NonNull
  private final SingleTransformer singleTransformer = upstream -> upstream
      .compose(RxLifecycle.bindUntilEvent(lifecycle(), FragmentEvent.DESTROY_VIEW))
      .observeOn(AndroidSchedulers.mainThread());

  @Override
  public boolean isViewAlive() {
    return isAdded() && getActivity() != null;
  }

  @NonNull
  @Override
  public Completable keepAliveWhileVisible(@NonNull Completable source) {
    return source.compose(RxLifecycle.bindUntilEvent(lifecycle(), FragmentEvent.DESTROY_VIEW))
        .observeOn(AndroidSchedulers.mainThread());
  }

  @NonNull
  @Override
  public <T> Flowable<T> keepAliveWhileVisible(@NonNull Flowable<T> source) {
    return source.compose(RxLifecycle.bindUntilEvent(lifecycle(), FragmentEvent.DESTROY_VIEW))
        .observeOn(AndroidSchedulers.mainThread());
  }

  @NonNull
  @Override
  public <T> Maybe<T> keepAliveWhileVisible(@NonNull Maybe<T> source) {
    return source.compose(RxLifecycle.bindUntilEvent(lifecycle(), FragmentEvent.DESTROY_VIEW))
        .observeOn(AndroidSchedulers.mainThread());
  }

  @NonNull
  @Override
  public <T> Observable<T> keepAliveWhileVisible(@NonNull Observable<T> source) {
    return source.compose(RxLifecycle.bindUntilEvent(lifecycle(), FragmentEvent.DESTROY_VIEW))
        .observeOn(AndroidSchedulers.mainThread());
  }

  @NonNull
  @Override
  public <T> SingleTransformer<T, T> keepAliveWhileVisible() {
    //noinspection unchecked
    return (SingleTransformer<T, T>) singleTransformer;
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
