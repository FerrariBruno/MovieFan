package com.xmartlabs.moviefan.ui.main;

import android.support.annotation.NonNull;

import com.f2prateek.dart.HensonNavigable;
import com.xmartlabs.bigbang.ui.BaseFragment;
import com.xmartlabs.bigbang.ui.SingleFragmentActivity;

/**
 * Created by bruno on 12/22/17.
 */
@HensonNavigable
public class HomeActivity extends SingleFragmentActivity {
  @NonNull
  @Override
  protected BaseFragment createFragment() {
    return new TabsFragmentBuilder().build();
  }
}
