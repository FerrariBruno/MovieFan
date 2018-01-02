package com.xmartlabs.moviefan.ui.main;

import android.support.annotation.NonNull;

import com.xmartlabs.moviefan.controller.genres.GenreController;
import com.xmartlabs.moviefan.helper.GeneralSingleSubscriber;
import com.xmartlabs.moviefan.model.Genre;
import com.xmartlabs.moviefan.ui.common.MovieFanPresenter;
import com.xmartlabs.moviefan.ui.views.MovieFanFilterView;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import lombok.Builder;

/**
 * Created by bruno on 1/2/18.
 */
@Builder
public class TabsPresenter extends MovieFanPresenter<TabsView> {
  private static final int FIRST_FRAGMENT = 0;
  private static final int OFFSCREEN_PAGE_LIMIT = 3;
  //TODO integrate ViewPagerAdapter
  private int lastFragment;

  @Inject
  GenreController genreController;

  //TODO integrate with filter dialog init
  private void getGenresFromService(@NonNull MovieFanFilterView filterView) {
    genreController
        .getAllGenres()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new GeneralSingleSubscriber<Map<Long, Genre>>() {
          @Override
          public void onSuccess(@NonNull Map<Long, Genre> genres) {
            filterView.initGenresSpinner(genres);
          }
        });
  }
}