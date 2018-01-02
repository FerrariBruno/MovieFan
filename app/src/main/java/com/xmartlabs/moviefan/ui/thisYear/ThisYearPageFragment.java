package com.xmartlabs.moviefan.ui.thisYear;

import android.support.annotation.NonNull;

import com.hannesdorfmann.fragmentargs.annotation.FragmentWithArgs;
import com.xmartlabs.moviefan.ui.main.MovieFanPageBaseFragment;

/**
 * Created by bruno on 12/22/17.
 */
@FragmentWithArgs
public class ThisYearPageFragment extends MovieFanPageBaseFragment<ThisYearPageView, ThisYearPagePresenter> {
  @NonNull
  @Override
  protected ThisYearPagePresenter createPresenter() {
    return ThisYearPagePresenter.builder().build();
  }
}
