package com.xmartlabs.moviefan.ui.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;

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
  private final int OFFSCREEN_PAGE_LIMIT = 3;

  @BindView(R.id.filter_button)
  Button filterButton;
  @BindView(R.id.viewpager)
  ViewPager viewPager;
  @BindView(R.id.sliding_tabs)
  TabLayout tabLayout;

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    initViewPagerAdapter();
    initButtonFilterButtonClickListener();
  }

  private void initViewPagerAdapter() {
    viewPager.setAdapter(new FilmsViewPagerAdapter(getFragmentManager(), getContext()));
    viewPager.setOffscreenPageLimit(OFFSCREEN_PAGE_LIMIT);
    tabLayout.setupWithViewPager(viewPager);
  }

  private void initButtonFilterButtonClickListener() {
    //TODO add behaviour for filter button click
    filterButton.setOnClickListener(view -> {  });
  }

  @Override
  protected int getLayoutResId() {
    return R.layout.tabs_fragment;
  }
}
