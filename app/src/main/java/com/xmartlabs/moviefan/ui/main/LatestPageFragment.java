package com.xmartlabs.moviefan.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xmartlabs.moviefan.R;
import com.xmartlabs.moviefan.ui.common.BaseFragment;

/**
 * Created by bruno on 12/1/17.
 */
public class LatestPageFragment extends BaseFragment {
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    return super.onCreateView(inflater, container, savedInstanceState);
  }

  @Override
  protected int getLayoutResId() {
    return R.layout.latest_page_fragment;
  }
}
