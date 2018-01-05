package com.xmartlabs.moviefan.ui.main;

import android.support.annotation.NonNull;

import com.annimon.stream.Optional;
import com.xmartlabs.moviefan.R;
import com.xmartlabs.moviefan.model.Genre;
import com.xmartlabs.moviefan.ui.common.MovieFanFragment;
import com.xmartlabs.moviefan.ui.common.OnFilterAppliedListener;

/**
 * Created by bruno on 12/22/17.
 */
public abstract class MovieFanPageBaseFragment<V extends MovieFanPageBaseView, P extends MovieFanPageBasePresenter<V>>
    extends MovieFanFragment<V, P> implements MovieFanPageBaseView, OnFilterAppliedListener {
  private boolean adultContent = false;
  @NonNull
  private Optional<Genre> genre = Optional.empty();

  //TODO update the recycler view after applying filters
  @Override
  public void onFilterApplied(@NonNull Optional<Genre> genre, boolean adultContent) {
    this.genre = genre;
    this.adultContent = adultContent;
  }

  @Override
  protected int getLayoutResId() {
    return R.layout.fragment_movie_page;
  }
}
