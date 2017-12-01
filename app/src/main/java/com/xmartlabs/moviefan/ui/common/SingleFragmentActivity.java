package com.xmartlabs.moviefan.ui.common;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;

import com.xmartlabs.moviefan.R;

import butterknife.BindView;

public abstract class SingleFragmentActivity extends BaseAppCompatActivity {
  @BindView(R.id.toolbar)
  Toolbar toolbar;

  @NonNull
  protected abstract BaseFragment createFragment();

  @LayoutRes
  protected int getLayoutResId() {
    return R.layout.activity_fragment;
  }

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(getLayoutResId());

    Fragment fragment = getSingleFragment();
    if (fragment == null) {
      fragment = createFragment();
      getSupportFragmentManager()
          .beginTransaction()
          .add(R.id.fragment_container, fragment)
          .commit();
    }

    setSupportActionBar(toolbar);
  }

  @Nullable
  protected Fragment getSingleFragment() {
    FragmentManager fragmentManager = getSupportFragmentManager();
    return fragmentManager.findFragmentById(R.id.fragment_container);
  }
}
