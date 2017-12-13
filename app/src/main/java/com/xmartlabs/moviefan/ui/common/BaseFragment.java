package com.xmartlabs.moviefan.ui.common;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.CheckResult;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hannesdorfmann.fragmentargs.FragmentArgs;
import com.trello.rxlifecycle2.RxLifecycle;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.trello.rxlifecycle2.components.support.RxFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.SingleTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;

public abstract class BaseFragment extends RxFragment {
  private Unbinder unbinder;

  @LayoutRes
  protected abstract int getLayoutResId();

  @NonNull
  @SuppressWarnings("unchecked")
  private final SingleTransformer singleTransformer = upstream -> upstream
      .observeOn(AndroidSchedulers.mainThread())
      .compose(RxLifecycle.bindUntilEvent(lifecycle(), FragmentEvent.DESTROY_VIEW));

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    FragmentArgs.inject(this);
  }

  @NonNull
  @CallSuper
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(getLayoutResId(), container, false);
    unbinder = ButterKnife.bind(this, view);
    return view;
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    unbinder.unbind();
  }

  @CheckResult
  @NonNull
  protected <T> SingleTransformer<T, T> prepareSingleForSubscription(){
    //noinspection unchecked
    return (SingleTransformer<T, T>) singleTransformer;
  }
}
