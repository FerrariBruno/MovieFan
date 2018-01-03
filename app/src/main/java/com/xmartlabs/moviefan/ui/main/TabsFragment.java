package com.xmartlabs.moviefan.ui.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.hannesdorfmann.fragmentargs.annotation.FragmentWithArgs;
import com.xmartlabs.moviefan.R;
import com.xmartlabs.moviefan.ui.common.MovieFanFragment;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by bruno on 12/22/17.
 */
@FragmentWithArgs
public class TabsFragment extends MovieFanFragment<TabsView, TabsPresenter> implements TabsView {
  @BindView(R.id.viewpager)
  ViewPager viewPager;
  @BindView(R.id.sliding_tabs)
  TabLayout tabLayout;

  @Inject
  TabsPresenter tabsPresenter;

  @NonNull
  @Override
  protected TabsPresenter createPresenter() {
    return tabsPresenter;
  }

  @Override
  protected int getLayoutResId() {
    return R.layout.fragment_home;
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    setHasOptionsMenu(true);
  }

  @Override
  public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
    super.onCreateOptionsMenu(menu, inflater);
    inflater.inflate(R.menu.menu_main, menu);
  }

  @Override
  public boolean onOptionsItemSelected(@NonNull MenuItem item) {
    switch (item.getItemId()) {
      case R.id.filter_button:
        tabsPresenter.onFilterButtonPressed();
        break;
      default:
        break;
    }
    return false;
  }
}
