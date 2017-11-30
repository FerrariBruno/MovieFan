package com.xmartlabs.moviefan.ui.slidingtabs;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.xmartlabs.moviefan.ui.main.PageFragment;

/**
 * Created by bruno on 11/30/17.
 */
public class FragmentAdapter extends FragmentPagerAdapter {
  private final int PAGE_COUNT = 3;
  private Context context;
  private String tabTitles[] = new String[] { "LATEST", "THIS YEAR", "1985" };

  public FragmentAdapter(FragmentManager fm, Context context) {
    super(fm);
    this.context = context;
  }

  @Override
  public int getCount() {
    return PAGE_COUNT;
  }

  @Override
  public Fragment getItem(int position) {
    return PageFragment.newInstance(position + 1);
  }

  @Override
  public CharSequence getPageTitle(int position) {
    // Generate title based on item position
    return tabTitles[position];
  }
}
