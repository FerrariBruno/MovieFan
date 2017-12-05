package com.xmartlabs.moviefan.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.hannesdorfmann.fragmentargs.annotation.FragmentWithArgs;
import com.xmartlabs.moviefan.R;
import com.xmartlabs.moviefan.ui.common.BaseFragment;
import com.xmartlabs.moviefan.ui.slidingtabs.FilmsViewPagerAdapter;

import butterknife.BindView;

/**
 * Created by bruno on 11/30/17.
 */
@FragmentWithArgs
public class TabsFragment extends BaseFragment {
  private final int OFFSCREEN_PAGE_LIMIT = 4;

  @BindView(R.id.viewpager)
  ViewPager viewPager;
  @BindView(R.id.sliding_tabs)
  TabLayout tabLayout;

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    initViewPagerAdapter();
  }

  private void initViewPagerAdapter() {
    viewPager.setAdapter(new FilmsViewPagerAdapter(getFragmentManager(), getContext()));
    viewPager.setOffscreenPageLimit(OFFSCREEN_PAGE_LIMIT);
    tabLayout.setupWithViewPager(viewPager);
  }

  @Override
  protected int getLayoutResId() {
    return R.layout.tabs_fragment;
  }
}
