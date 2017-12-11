package com.xmartlabs.moviefan.services;

import com.xmartlabs.moviefan.ui.models.GenreResponse;

import io.reactivex.Single;
import retrofit2.http.GET;

/**
 * Created by bruno on 12/8/17.
 */
public interface GenresService {
  String GET_GENRES = "genre/movie/list";

  @GET(GET_GENRES)
  Single<GenreResponse> getGenres();
}
