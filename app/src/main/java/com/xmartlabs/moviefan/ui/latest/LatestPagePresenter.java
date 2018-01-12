package com.xmartlabs.moviefan.ui.latest;

import android.support.annotation.NonNull;

import com.annimon.stream.Optional;
import com.xmartlabs.moviefan.model.Film;
import com.xmartlabs.moviefan.model.Genre;
import com.xmartlabs.moviefan.ui.main.FilmsRecyclerViewAdapter;
import com.xmartlabs.moviefan.ui.main.MovieFanPageBasePresenter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Single;

/**
 * Created by bruno on 1/2/18.
 */
public class LatestPagePresenter extends MovieFanPageBasePresenter<LatestPageView> {
  private static final int FIRST_PAGE = 1;
  @Inject
  public LatestPagePresenter() { }

  @NonNull
  @Override
  protected FilmsRecyclerViewAdapter createFilmsAdapter() {
    return new FilmsRecyclerViewAdapter();
  }

  @NonNull
  @Override
  protected Flowable<List<Film>> requestFilms(int pageNumber, @NonNull Optional<Genre> genre, boolean adultContent) {
    return pageNumber == FIRST_PAGE
        ? filmController
            .getFirstPageOfLatestFilms(genre, adultContent)
        : filmController
            .getLatestFilms(pageNumber, genre, adultContent)
            .toFlowable();
  }
}
