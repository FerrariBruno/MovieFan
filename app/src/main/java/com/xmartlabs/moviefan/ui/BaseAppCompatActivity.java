package com.xmartlabs.moviefan.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.f2prateek.dart.Dart;
import com.trello.rxlifecycle2.components.RxActivity;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.xmartlabs.bigbang.core.Injector;

import bullet.ObjectGraph;

public abstract class BaseAppCompatActivity extends AppCompatActivity {
  protected Context getContext() {
    return this;
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Dart.inject(this);
    Injector.inject(this);
  }

  /**
   * Removes the given fragment from the view.
   *
   * @param fragment the fragment to be removed
   */
  public void removeFragment(@NonNull Fragment fragment) {
    getSupportFragmentManager().beginTransaction().remove(fragment).commit();
  }

  /** Hides the keyboard, if visible. */
  protected void hideKeyboard() {
    InputMethodManager inputMethodManager = (InputMethodManager) this.getSystemService(Activity.INPUT_METHOD_SERVICE);
    View currentFocus = this.getCurrentFocus();
    if (currentFocus != null) {
      inputMethodManager.hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
    }
  }
}
