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
import com.xmartlabs.moviefan.controller.GenreController;
import com.xmartlabs.moviefan.ui.common.BaseFragment;
import com.xmartlabs.moviefan.ui.common.GeneralSingleSubscriber;
import com.xmartlabs.moviefan.ui.common.MovieFanFilterView;
import com.xmartlabs.moviefan.ui.common.OnFilterAppliedListener;
import com.xmartlabs.moviefan.ui.models.Genre;
import com.xmartlabs.moviefan.ui.slidingtabs.FilmsViewPagerAdapter;

import java.util.Map;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by bruno on 11/30/17.
 */
@FragmentWithArgs
public class TabsFragment extends BaseFragment {
  private static final int FIRST_FRAGMENT = 0;
  private static final int OFFSCREEN_PAGE_LIMIT = 3;

  @Nullable
  private FilmsViewPagerAdapter filmsViewPagerAdapter;
  private int lastFragment;

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
    lastFragment = filmsViewPagerAdapter.getLastFragmentsNumber();
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
    return false;
  }

  private void initFilterDialog() {
    BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getActivity());
    MovieFanFilterView filterView = new MovieFanFilterView(getContext());
    filterView.setOnFilterAppliedListener((genreId, adultContent) -> {
      //noinspection ConstantConditions
      Stream.rangeClosed(FIRST_FRAGMENT, lastFragment)
          .map(filmsViewPagerAdapter::getItem)
          .filter(fragment -> fragment instanceof OnFilterAppliedListener)
          .map(fragment -> (OnFilterAppliedListener) fragment)
          .forEach(listener -> listener.onFilterApplied(genreId, adultContent));
      bottomSheetDialog.dismiss();
    });
    getGenresFromService(filterView);
    bottomSheetDialog.setContentView(filterView);
    bottomSheetDialog.show();
  }

  private void getGenresFromService(@NonNull MovieFanFilterView filterView) {
    GenreController.getInstance()
        .getAllGenres()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new GeneralSingleSubscriber<Map<Long, Genre>>() {
          @Override
          public void onSuccess(@NonNull Map<Long, Genre> genres) {
            filterView.initGenresSpinner(genres);
          }
        });
  }
}
