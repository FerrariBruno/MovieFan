package com.xmartlabs.moviefan.ui.main;

import com.hannesdorfmann.fragmentargs.annotation.FragmentWithArgs;
import com.xmartlabs.moviefan.R;
import com.xmartlabs.moviefan.ui.common.BaseFragment;

/**
 * Created by bruno on 12/1/17.
 */
@FragmentWithArgs
public class SpecificYearPageFragment extends BaseFragment {
  @Override
  protected int getLayoutResId() {
    return R.layout.specific_year_page_fragment;
  }
}
