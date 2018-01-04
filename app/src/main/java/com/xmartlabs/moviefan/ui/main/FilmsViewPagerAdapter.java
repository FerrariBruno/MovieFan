package com.xmartlabs.moviefan.ui.main;

import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.xmartlabs.moviefan.MovieFanApplication;
import com.xmartlabs.moviefan.R;
import com.xmartlabs.moviefan.ui.latest.LatestPageFragmentBuilder;
import com.xmartlabs.moviefan.ui.specificYear.SpecificYearPageFragmentBuilder;
import com.xmartlabs.moviefan.ui.thisYear.ThisYearPageFragmentBuilder;

import java.util.Locale;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Created by bruno on 01/04/18.
 */
public class FilmsViewPagerAdapter extends FragmentPagerAdapter {
  @NonNull
  private final MovieFanPageBaseFragment LATEST_PAGE_FRAGMENT = new LatestPageFragmentBuilder().build();
  @NonNull
  private final MovieFanPageBaseFragment SPECIFIC_YEAR_PAGE_FRAGMENT = new SpecificYearPageFragmentBuilder().build();
  @NonNull
  private final MovieFanPageBaseFragment THIS_YEAR_PAGE_FRAGMENT = new ThisYearPageFragmentBuilder().build();

  FilmsViewPagerAdapter(FragmentManager fragmentManager) {
    super(fragmentManager);
  }

  @RequiredArgsConstructor
  private enum Page {
    LATEST(R.string.home_view_latest),
    SPECIFIC_YEAR(R.string.home_view_1991),
    THIS_YEAR(R.string.home_view_this_year),
    ;

    private static final int SIZE = Page.values().length;

    @Getter
    @StringRes
    private final int titleResourceId;
  }

  @NonNull
  private Fragment getFragmentFromPage(@NonNull Page page) {
    switch (page) {
      case LATEST:
        return LATEST_PAGE_FRAGMENT;
      case THIS_YEAR:
        return THIS_YEAR_PAGE_FRAGMENT;
      case SPECIFIC_YEAR:
        return SPECIFIC_YEAR_PAGE_FRAGMENT;
      default:
        throw new ExceptionInInitializerError(String.format(Locale.US,"viewType: %d was not found",
            page.ordinal()));
    }
  }

  @Override
  public int getCount() {
    return Page.SIZE;
  }

  @NonNull
  @Override
  public Fragment getItem(int position) {
    return getFragmentFromPage(Page.values()[position]);
  }

  @NonNull
  @Override
  public CharSequence getPageTitle(int position) {
    return MovieFanApplication.getContext()
        .getString(Page.values()[position].getTitleResourceId());
  }

  int getLastFragmentsNumber() {
    return getCount() - 1;
  }
}

