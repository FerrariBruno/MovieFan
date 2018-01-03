package com.xmartlabs.moviefan.ui.main;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;

import com.annimon.stream.Optional;
import com.xmartlabs.moviefan.controller.films.FilmController;
import com.xmartlabs.moviefan.model.Film;
import com.xmartlabs.moviefan.model.Genre;
import com.xmartlabs.moviefan.ui.common.MovieFanPresenter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Created by bruno on 1/2/18.
 */
public abstract class MovieFanPageBasePresenter<T extends MovieFanPageBaseView> extends MovieFanPresenter<T> {
  @Inject
  protected FilmController filmController;

  @CheckResult
  @NonNull
  protected abstract Single<List<Film>> requestFilms(int pageNumber, @NonNull Optional<Genre> genre, boolean adultContent);
}
