package com.xmartlabs.moviefan.ui.thisYear;

import android.support.annotation.NonNull;

import com.hannesdorfmann.fragmentargs.annotation.FragmentWithArgs;
import com.xmartlabs.moviefan.ui.main.MovieFanPageBaseFragment;

import javax.inject.Inject;

/**
 * Created by bruno on 12/22/17.
 */
@FragmentWithArgs
public class ThisYearPageFragment extends MovieFanPageBaseFragment<ThisYearPageView, ThisYearPagePresenter>
    implements ThisYearPageView {
  @Inject
  ThisYearPagePresenter thisYearPagePresenter;

  @NonNull
  @Override
  protected ThisYearPagePresenter createPresenter() {
    return thisYearPagePresenter;
  }
}
