package com.xmartlabs.moviefan.ui.main;

import com.xmartlabs.moviefan.R;
import com.xmartlabs.moviefan.ui.common.MovieFanFragment;

/**
 * Created by bruno on 12/22/17.
 */
public abstract class MovieFanPageBaseFragment<V extends MovieFanPageBaseView, P extends MovieFanPageBasePresenter<V>>
    extends MovieFanFragment<V, P> implements MovieFanPageBaseView {
  @Override
  protected int getLayoutResId() {
    return R.layout.fragment_movie_page;
  }
}
