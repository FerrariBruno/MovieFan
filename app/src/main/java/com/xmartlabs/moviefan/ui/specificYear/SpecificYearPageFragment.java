package com.xmartlabs.moviefan.ui.specificYear;

import android.support.annotation.NonNull;

import com.hannesdorfmann.fragmentargs.annotation.FragmentWithArgs;
import com.xmartlabs.moviefan.ui.main.MovieFanPageBaseFragment;

import javax.inject.Inject;

/**
 * Created by bruno on 12/22/17.
 */
@FragmentWithArgs
public class SpecificYearPageFragment extends MovieFanPageBaseFragment<SpecificYearPageView, SpecificYearPagePresenter>
    implements SpecificYearPageView {
  @Inject
  SpecificYearPagePresenter specificYearPagePresenter;

  @NonNull
  @Override
  protected SpecificYearPagePresenter createPresenter() {
    return specificYearPagePresenter;
  }
}
