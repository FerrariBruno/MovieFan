package com.xmartlabs.moviefan.ui.main;

import android.support.annotation.NonNull;

import com.annimon.stream.Optional;
import com.annimon.stream.Stream;
import com.xmartlabs.moviefan.controller.genres.GenreController;
import com.xmartlabs.moviefan.helper.GeneralSingleSubscriber;
import com.xmartlabs.moviefan.model.Genre;
import com.xmartlabs.moviefan.ui.common.MovieFanPresenter;
import com.xmartlabs.moviefan.ui.common.OnFilterAppliedListener;
import com.xmartlabs.moviefan.ui.views.MovieFanFilterView;

import java.util.Map;

import javax.inject.Inject;

/**
 * Created by bruno on 1/2/18.
 */
public class TabsPresenter extends MovieFanPresenter<TabsView> {
  @Inject
  GenreController genreController;
  @Inject
  TabsPresenter() { }

  void getGenresFromService(@NonNull MovieFanFilterView filterView) {
    executeOnViewIfPresent(view ->
        genreController.getAllGenres()
        .compose(view.keepAliveWhileVisibleSingle())
        .subscribe(new GeneralSingleSubscriber<Map<Long, Genre>>() {
          @Override
          public void onSuccess(@NonNull Map<Long, Genre> genres) {
            filterView.initGenresSpinner(genres);
          }
        }));
  }

  void onFilterSelected(Optional<Genre> genre, boolean adultContent, FilmsViewPagerAdapter filmsViewPagerAdapter) {
    Stream.rangeClosed(FilmsViewPagerAdapter.FIRST_FRAGMENT, filmsViewPagerAdapter.getLastFragmentsNumber())
        .map(filmsViewPagerAdapter::getItem)
        .filter(fragment -> fragment instanceof OnFilterAppliedListener)
        .map(fragment -> (OnFilterAppliedListener) fragment)
        .forEach(listener -> listener.onFilterApplied(genre, adultContent));
  }
}
