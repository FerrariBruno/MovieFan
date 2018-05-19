package com.xmartlabs.moviefan.ui.specificYear;

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
public class SpecificYearPagePresenter extends MovieFanPageBasePresenter<SpecificYearPageView> {
  @Inject
  public SpecificYearPagePresenter() { }

  @NonNull
  @Override
  protected FilmsRecyclerViewAdapter createFilmsAdapter() {
    return new FilmsRecyclerViewAdapter();
  }

  @NonNull
  @Override
  protected Flowable<List<Film>> requestFilms(int pageNumber, @NonNull Optional<Genre> genre, boolean adultContent) {
    return filmController
        .getSpecificYearFilms(pageNumber, genre, adultContent)
        .toFlowable();
  }
}
