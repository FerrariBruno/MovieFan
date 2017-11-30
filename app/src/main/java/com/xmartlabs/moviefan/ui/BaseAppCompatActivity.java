package com.xmartlabs.moviefan.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.inputmethod.InputMethodManager;
import com.annimon.stream.Optional;
import com.f2prateek.dart.Dart;

public abstract class BaseAppCompatActivity extends AppCompatActivity {
  protected Context getContext() {
    return this;
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Dart.inject(this);
  }

  public void removeFragment(@NonNull Fragment fragment) {
    getSupportFragmentManager().beginTransaction().remove(fragment).commit();
  }

  protected void hideKeyboard() {
    InputMethodManager inputMethodManager = (InputMethodManager) this.getSystemService(Activity.INPUT_METHOD_SERVICE);
    //noinspection ConstantConditions
    Optional.ofNullable(getCurrentFocus())
        .ifPresent(currentFocus -> inputMethodManager.hideSoftInputFromWindow(currentFocus.getWindowToken(), 0));
  }
}
