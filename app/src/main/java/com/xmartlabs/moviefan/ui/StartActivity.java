package com.xmartlabs.moviefan.ui;

import android.content.Intent;
import android.os.Bundle;

import com.f2prateek.dart.HensonNavigable;
import com.xmartlabs.moviefan.ui.common.BaseAppCompatActivity;

@HensonNavigable
public class StartActivity extends BaseAppCompatActivity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Intent startSingleFragmentActivity = Henson.with(this)
        .gotoHomeActivity()
        .build()
        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
    startActivity(startSingleFragmentActivity);
  }
}
