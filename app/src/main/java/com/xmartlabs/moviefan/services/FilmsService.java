package com.xmartlabs.moviefan.services;

import com.xmartlabs.moviefan.ui.models.FilmResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by bruno on 12/8/17.
 */
public interface FilmsService {
  String GET_MOVIES = "discover/movie";

  String QUERY_SORT_BY = "sort_by";
  String QUERY_RELEASE_DATE_LTE = "release_date.lte";
  String QUERY_ADULT_CONTENT = "include_adult";
  String QUERY_YEAR = "year";
  String QUERY_GENRES = "with_genres";
  String QUERY_PAGE = "page";

  @GET(GET_MOVIES)
  Single<FilmResponse> getLatestFilms(@Query(QUERY_SORT_BY) String sortBy, @Query(QUERY_RELEASE_DATE_LTE) String todaysDate,
                                      @Query(QUERY_ADULT_CONTENT) String include_adult, @Query(QUERY_YEAR) String year,
                                      @Query(QUERY_GENRES) String genres, @Query(QUERY_PAGE) int page);

  @GET(GET_MOVIES)
  Single<FilmResponse> getSpecificYearFilms(@Query(QUERY_YEAR) int year, @Query(QUERY_ADULT_CONTENT) boolean include_adult,
                                          @Query(QUERY_GENRES) String genres, @Query(QUERY_PAGE) int page);
}
