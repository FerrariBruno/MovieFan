package com.xmartlabs.moviefan.ui.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.SwitchCompat;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Spinner;

import com.annimon.stream.Stream;
import com.hannesdorfmann.fragmentargs.annotation.FragmentWithArgs;
import com.xmartlabs.moviefan.R;
import com.xmartlabs.moviefan.ui.common.BaseFragment;
import com.xmartlabs.moviefan.ui.common.MovieFanFilterView;
import com.xmartlabs.moviefan.ui.common.OnFilterAppliedListener;
import com.xmartlabs.moviefan.ui.slidingtabs.FilmsViewPagerAdapter;

import butterknife.BindView;

/**
 * Created by bruno on 11/30/17.
 */
@FragmentWithArgs
public class TabsFragment extends BaseFragment {
  private FilmsViewPagerAdapter filmsViewPagerAdapter;
  private static final int FIRST_FRAGMENT = 1;
  private static final int OFFSCREEN_PAGE_LIMIT = 3;

  @BindView(R.id.viewpager)
  ViewPager viewPager;
  @BindView(R.id.sliding_tabs)
  TabLayout tabLayout;

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    initViewPagerAdapter();
    setHasOptionsMenu(true);
  }

  private void initViewPagerAdapter() {
    filmsViewPagerAdapter = new FilmsViewPagerAdapter(getFragmentManager(), getContext());
    viewPager.setAdapter(filmsViewPagerAdapter);
    viewPager.setOffscreenPageLimit(OFFSCREEN_PAGE_LIMIT);
    tabLayout.setupWithViewPager(viewPager);
  }
  
  @Override
  protected int getLayoutResId() {
    return R.layout.fragment_home;
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
        initFilterDialog();
        break;
      default:
        break;
    }
    return true;
  }

  private void initFilterDialog() {
    BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getActivity());
    MovieFanFilterView filterView = new MovieFanFilterView(getContext());
    filterView.setFilterApplyListener(new OnFilterAppliedListener() {
      @Override
      public void onFilterApplied(String genreId, String adultContent) {
        Stream.range(FIRST_FRAGMENT, filmsViewPagerAdapter.getCount())
            .map(integer -> filmsViewPagerAdapter.getItem(integer))
            .filter(fragment -> fragment instanceof OnFilterAppliedListener)
            .map(fragment -> (OnFilterAppliedListener) fragment)
            .forEach(listener -> listener.onFilterApplied(genreId, adultContent));
      }
    });
    bottomSheetDialog.setContentView(filterView);
    bottomSheetDialog.show();
  }
}
