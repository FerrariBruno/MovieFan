package com.xmartlabs.moviefan.ui.latest;

import android.support.annotation.NonNull;

import com.annimon.stream.Optional;
import com.xmartlabs.moviefan.model.Film;
import com.xmartlabs.moviefan.model.Genre;
import com.xmartlabs.moviefan.ui.main.FilmsRecyclerViewAdapter;
import com.xmartlabs.moviefan.ui.main.MovieFanPageBasePresenter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Created by bruno on 1/2/18.
 */
public class LatestPagePresenter extends MovieFanPageBasePresenter<LatestPageView> {
  @Inject
  LatestPagePresenter() { }

  @NonNull
  @Override
  protected FilmsRecyclerViewAdapter createFilmsAdapter() {
    //TODO integrate with recyclerview
    return null;
  }

  @NonNull
  @Override
  protected Single<List<Film>> requestFilms(int pageNumber, @NonNull Optional<Genre> genre, boolean adultContent) {
    return filmController.getLatestFilms(pageNumber, genre, adultContent);
  }
}
