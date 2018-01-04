package com.xmartlabs.moviefan.ui.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.f2prateek.dart.HensonNavigable;
import com.xmartlabs.bigbang.ui.BaseFragment;
import com.xmartlabs.bigbang.ui.SingleFragmentActivity;
import com.xmartlabs.moviefan.R;

/**
 * Created by bruno on 12/22/17.
 */
@HensonNavigable
public class HomeActivity extends SingleFragmentActivity {
  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setupToolbar();
  }

  @NonNull
  @Override
  protected BaseFragment createFragment() {
    return new TabsFragmentBuilder().build();
  }

  private void setupToolbar() {
    setSupportActionBar(findViewById(R.id.toolbar));
    //noinspection ConstantConditions
    getSupportActionBar().setDisplayShowTitleEnabled(false);
  }
}
