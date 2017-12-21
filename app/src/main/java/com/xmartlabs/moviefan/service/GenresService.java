package com.xmartlabs.moviefan.service;

import com.xmartlabs.moviefan.model.GenreResponse;

import io.reactivex.Single;
import retrofit2.http.GET;

/**
 * Created by bruno on 12/21/17.
 */
public interface GenresService {
  String GET_GENRES = "genre/movie/list";

  @GET(GET_GENRES)
  Single<GenreResponse> getGenres();
}
