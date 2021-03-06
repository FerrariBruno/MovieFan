package com.xmartlabs.moviefan.ui.latest;

import android.support.annotation.NonNull;

import com.hannesdorfmann.fragmentargs.annotation.FragmentWithArgs;
import com.xmartlabs.moviefan.ui.main.MovieFanPageBaseFragment;

import javax.inject.Inject;

/**
 * Created by bruno on 12/22/17.
 */
@FragmentWithArgs
public class LatestPageFragment extends MovieFanPageBaseFragment<LatestPageView, LatestPagePresenter>
    implements LatestPageView {
  @Inject
  LatestPagePresenter latestPagePresenter;

  @NonNull
  @Override
  protected LatestPagePresenter createPresenter() {
    return latestPagePresenter;
  }
}
