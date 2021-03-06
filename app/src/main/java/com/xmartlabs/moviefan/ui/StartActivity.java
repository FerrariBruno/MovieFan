package com.xmartlabs.moviefan.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.f2prateek.dart.HensonNavigable;
import com.xmartlabs.bigbang.ui.BaseAppCompatActivity;
import com.xmartlabs.moviefan.controller.SessionController;

import javax.inject.Inject;

@HensonNavigable
public class StartActivity extends BaseAppCompatActivity {
  //TODO add the onboarding check
  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Intent startSingleFragmentActivity = Henson.with(this)
        .gotoHomeActivity()
        .build()
        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
    startActivity(startSingleFragmentActivity);
  }
}
