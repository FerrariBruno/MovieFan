package com.xmartlabs.moviefan.ui.main;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xmartlabs.moviefan.R;
import com.xmartlabs.moviefan.ui.common.BaseFragment;
import com.xmartlabs.moviefan.ui.slidingtabs.FragmentAdapter;

/**
 * Created by bruno on 11/30/17.
 */
public class TabsFragment extends BaseFragment {
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View v = super.onCreateView(inflater,container,savedInstanceState);
    ViewPager viewPager = v.findViewById(R.id.viewpager);

    viewPager.setAdapter(new FragmentAdapter(getFragmentManager(), getContext()));
    TabLayout tabLayout = v.findViewById(R.id.sliding_tabs);
    tabLayout.setupWithViewPager(viewPager);
    return v;
  }
  @Override
  protected int getLayoutResId() {
    return R.layout.tabs_fragment;
  }
}
