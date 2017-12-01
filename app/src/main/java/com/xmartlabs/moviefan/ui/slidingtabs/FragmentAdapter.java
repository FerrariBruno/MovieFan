package com.xmartlabs.moviefan.ui.slidingtabs;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.xmartlabs.moviefan.R;
import com.xmartlabs.moviefan.ui.main.LatestPageFragment;
import com.xmartlabs.moviefan.ui.main.SpecificYearPageFragment;
import com.xmartlabs.moviefan.ui.main.ThisYearPageFragment;

/**
 * Created by bruno on 11/30/17.
 */
public class FragmentAdapter extends FragmentPagerAdapter {
  private Context context;

  private enum PageFragment {
    LATEST(new LatestPageFragment(), R.string.home_view_latest),
    SPECIFIC_YEAR(new SpecificYearPageFragment(), R.string.home_view_1985),
    THIS_YEAR(new ThisYearPageFragment(), R.string.home_view_this_year);

    private Fragment fragment;
    private int titleResourceID;
    private static final int size = PageFragment.values().length;

    PageFragment(Fragment fragment, int titleResourceID){
      this.fragment = fragment;
      this.titleResourceID = titleResourceID;
    }
  }

  public FragmentAdapter(FragmentManager fm, Context context) {
    super(fm);
    this.context = context;
  }

  @Override
  public int getCount() {
    return PageFragment.size;
  }

  @Override
  public Fragment getItem(int position) {
    return PageFragment.values()[position].fragment;
  }

  @Override
  public CharSequence getPageTitle(int position) {
    return context.getString(PageFragment.values()[position].titleResourceID);
  }
}
