package com.xmartlabs.moviefan.ui.slidingtabs;

import android.content.Context;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.xmartlabs.moviefan.R;
import com.xmartlabs.moviefan.ui.main.LatestPageFragment;
import com.xmartlabs.moviefan.ui.main.SpecificYearPageFragment;
import com.xmartlabs.moviefan.ui.main.ThisYearPageFragment;

import java.util.Locale;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Created by bruno on 12/22/17.
 */
public class FilmsViewPagerAdapter extends FragmentPagerAdapter {
  @NonNull
  private Context context;
  private final LatestPageFragment LATEST_PAGE_FRAGMENT = new LatestPageFragmentBuilder().build();
  private final SpecificYearPageFragment SPECIFIC_YEAR_PAGE_FRAGMENT = new SpecificYearPageFragmentBuilder().build();
  private final ThisYearPageFragment THIS_YEAR_PAGE_FRAGMENT = new ThisYearPageFragmentBuilder().build();

  @RequiredArgsConstructor
  private enum Page {
    LATEST(R.string.home_view_latest),
    SPECIFIC_YEAR(R.string.home_view_1991),
    THIS_YEAR(R.string.home_view_this_year),
    ;

    private static final int SIZE = Page.values().length;

    @StringRes
    private final int titleResourceId;
  }

  public FilmsViewPagerAdapter(@NonNull FragmentManager fm, @NonNull Context context) {
    super(fm);
    this.context = context;
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
    return context.getString(Page.values()[position].titleResourceId);
  }

  public int getLastFragmentsNumber() {
    return getCount() - 1;
  }
}
