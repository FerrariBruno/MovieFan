package com.xmartlabs.moviefan.ui.common;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.f2prateek.dart.Dart;

public abstract class BaseAppCompatActivity extends AppCompatActivity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Dart.inject(this);
  }
}
