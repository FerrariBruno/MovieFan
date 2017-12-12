package com.xmartlabs.moviefan.controller;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.xmartlabs.moviefan.retrofit.RestProvider;
import com.xmartlabs.moviefan.ui.models.Genre;
import com.xmartlabs.moviefan.ui.models.GenreResponse;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Single;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by bruno on 12/8/17.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GenreController extends BaseController {
  private static GenreController GENRE_CONTROLLER = new GenreController();

  @Getter
  @Nullable
  private Map<Long, Genre> genres;

  @Nullable
  private Single<Map<Long, Genre>> getGenresFromService = RestProvider.getInstance().provideGenresService().getGenres()
      .map(GenreResponse::getGenres)
      .flatMapObservable(Observable::fromIterable)
      .toMap(Genre::getId)
      .doOnSuccess(genres -> this.genres = genres)
      .toObservable()
      .share()
      .firstOrError();

  @NonNull
  static GenreController getInstance() {
    return GENRE_CONTROLLER;
  }

  @CheckResult
  @NonNull
  public Single<Map<Long, Genre>> getAllGenres() {
    return Single
        .defer(() -> genres == null
            ? getGenresFromService
            : Single.just(genres))
        .compose(applySingleIoSchedulers());
  }
}
