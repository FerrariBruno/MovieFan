package com.xmartlabs.moviefan.ui.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.annimon.stream.Stream;
import com.hannesdorfmann.fragmentargs.annotation.FragmentWithArgs;
import com.xmartlabs.moviefan.R;
import com.xmartlabs.moviefan.ui.common.MovieFanFragment;
import com.xmartlabs.moviefan.ui.common.OnFilterAppliedListener;
import com.xmartlabs.moviefan.ui.views.MovieFanFilterView;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by bruno on 12/22/17.
 */
@FragmentWithArgs
public class TabsFragment extends MovieFanFragment<TabsView, TabsPresenter> implements TabsView {
  private static final int FIRST_FRAGMENT = 0;
  private static final int OFFSCREEN_PAGE_LIMIT = 3;

  @BindView(R.id.viewpager)
  ViewPager viewPager;
  @BindView(R.id.sliding_tabs)
  TabLayout tabLayout;

  @Nullable
  private FilmsViewPagerAdapter filmsViewPagerAdapter;
  private int lastFragment;

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
    setupViewPagerAdapter();
    setHasOptionsMenu(true);
  }

  @Override
  public void setupViewPagerAdapter() {
    filmsViewPagerAdapter = new FilmsViewPagerAdapter(getFragmentManager());
    lastFragment = filmsViewPagerAdapter.getLastFragmentsNumber();
    viewPager.setAdapter(filmsViewPagerAdapter);
    viewPager.setOffscreenPageLimit(OFFSCREEN_PAGE_LIMIT);
    tabLayout.setupWithViewPager(viewPager);
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
        showFilterView();
        break;
      default:
        break;
    }
    return false;
  }

  @Override
  public void showFilterView() {
    BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getActivity());
    MovieFanFilterView filterView = new MovieFanFilterView(getContext());
    filterView.setOnFilterAppliedListener((genreId, adultContent) -> {
      //noinspection ConstantConditions
      Stream.rangeClosed(FIRST_FRAGMENT, lastFragment)
          .map(filmsViewPagerAdapter::getItem)
          //****************
          //FIXME unless I put the adapter on the presenter, this has to go here in the view, is this okay?
          //****************
          .filter(fragment -> fragment instanceof OnFilterAppliedListener)
          .map(fragment -> (OnFilterAppliedListener) fragment)
          .forEach(listener -> listener.onFilterApplied(genreId, adultContent));
      bottomSheetDialog.dismiss();
    });
    tabsPresenter.getGenresFromService(filterView);
    bottomSheetDialog.setContentView(filterView);
    bottomSheetDialog.show();
  }
}
