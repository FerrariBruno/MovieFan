package com.xmartlabs.moviefan.ui.main;

import android.support.annotation.NonNull;
import com.f2prateek.dart.HensonNavigable;
import com.xmartlabs.moviefan.ui.common.BaseFragment;
import com.xmartlabs.moviefan.ui.common.SingleFragmentActivity;

/**
 * Created by bruno on 11/30/17.
 */
@HensonNavigable
public class HomeActivity extends SingleFragmentActivity {
  @NonNull
  @Override
  protected BaseFragment createFragment() {
    return new TabsFragment();
  }
}
