package com.xmartlabs.moviefan.controller.genres;

import android.support.annotation.NonNull;

import com.xmartlabs.moviefan.controller.BaseServiceController;
import com.xmartlabs.moviefan.model.Genre;
import com.xmartlabs.moviefan.model.service.response.GenreResponse;
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

  @NonNull
  public Single<Map<Long, Genre>> getGenresFromService() {
    return genresService
        .getGenres()
        .map(GenreResponse::getGenres)
        .flatMapObservable(Observable::fromIterable)
        .toMap(Genre::getId);
  }
}
