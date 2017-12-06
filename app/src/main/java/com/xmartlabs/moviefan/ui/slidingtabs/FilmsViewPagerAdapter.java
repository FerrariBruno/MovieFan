package com.xmartlabs.moviefan.ui.slidingtabs;

import android.content.Context;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.xmartlabs.moviefan.R;
import com.xmartlabs.moviefan.ui.main.LatestPageFragment;
import com.xmartlabs.moviefan.ui.main.LatestPageFragmentBuilder;
import com.xmartlabs.moviefan.ui.main.SpecificYearPageFragment;
import com.xmartlabs.moviefan.ui.main.SpecificYearPageFragmentBuilder;
import com.xmartlabs.moviefan.ui.main.ThisYearPageFragment;
import com.xmartlabs.moviefan.ui.main.ThisYearPageFragmentBuilder;

import java.util.Locale;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Created by bruno on 11/30/17.
 */
public class FilmsViewPagerAdapter extends FragmentPagerAdapter {
  private Context context;
  private final LatestPageFragment latestPageFragment = new LatestPageFragmentBuilder()
      .build();
  private final SpecificYearPageFragment specificYearPageFragment = new SpecificYearPageFragmentBuilder()
      .build();
  private final ThisYearPageFragment thisYearPageFragment = new ThisYearPageFragmentBuilder()
      .build();

  @RequiredArgsConstructor
  private enum Page {
    LATEST(R.string.home_view_latest),
    THIS_YEAR(R.string.home_view_this_year),
    SPECIFIC_YEAR(R.string.home_view_1985),
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
        return latestPageFragment;
      case THIS_YEAR:
        return thisYearPageFragment;
      case SPECIFIC_YEAR:
        return specificYearPageFragment;
      default:
        throw new ExceptionInInitializerError(String.format(Locale.US,"viewType: %d was not found",
            page.ordinal()));
    }
  }

  @Override
  public int getCount() {
    return Page.SIZE;
  }

  @Override
  public Fragment getItem(int position) {
    return getFragmentFromPage(Page.values()[position]);
  }

  @Override
  public CharSequence getPageTitle(int position) {
    return context.getString(Page.values()[position].titleResourceId);
  }
}
