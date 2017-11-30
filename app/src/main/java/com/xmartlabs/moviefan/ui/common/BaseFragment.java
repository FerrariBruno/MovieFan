package com.xmartlabs.moviefan.ui.common;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.annimon.stream.Optional;
import com.hannesdorfmann.fragmentargs.FragmentArgs;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment {
  private Unbinder unbinder;

  @LayoutRes
  protected abstract int getLayoutResId();

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    FragmentArgs.inject(this);
  }

  @CallSuper
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(getLayoutResId(), container, false);
    unbinder = ButterKnife.bind(this, view);
    return view;
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    unbinder.unbind();
  }

  protected void showAlertError(int stringResId) {
    new AlertDialog.Builder(getContext())
        .setMessage(stringResId)
        .setNeutralButton(android.R.string.ok, null)
        .show();
  }

  protected void removeItselfFromActivity() {
    ((BaseAppCompatActivity) getActivity()).removeFragment(this);
  }

  protected void removeItselfFromParentFragment() {
    getParentFragment().getChildFragmentManager().beginTransaction().remove(this).commit();
  }

  protected void removeItselfFromParent() {
    Optional.ofNullable(getParentFragment())
        .ifPresentOrElse(parent -> removeItselfFromParentFragment(), this::removeItselfFromActivity);
  }
}
