package com.xmartlabs.moviefan.controller.genres;

import android.support.annotation.Nullable;

import com.xmartlabs.moviefan.controller.BaseServiceController;
import com.xmartlabs.moviefan.model.Genre;
import com.xmartlabs.moviefan.model.GenreResponse;
import com.xmartlabs.moviefan.service.GenresService;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by bruno on 12/21/17.
 */
public class GenreServiceController extends BaseServiceController<Long, Genre> {
  @Inject
  GenresService genresService;

  @Nullable
  public Single<Map<Long, Genre>> getGenresFromService() {
    return genresService
        .getGenres()
        .map(GenreResponse::getGenres)
        .flatMapObservable(Observable::fromIterable)
        .toMap(Genre::getId);
  }
}
