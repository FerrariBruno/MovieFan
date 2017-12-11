package com.xmartlabs.moviefan.controller;

import android.support.annotation.NonNull;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.xmartlabs.moviefan.retrofit.RestProvider;
import com.xmartlabs.moviefan.services.FilmsService;
import com.xmartlabs.moviefan.ui.models.Film;
import com.xmartlabs.moviefan.ui.models.FilmResponse;
import com.xmartlabs.moviefan.ui.models.Genre;
import com.xmartlabs.moviefan.ui.models.ListResponse;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Created by bruno on 12/6/17.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FilmController {
  private static final FilmController FILM_CONTROLLER = new FilmController();
  private SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

  private final int YEAR_1985 = 1985;
  private final String SORT_BY_QUERY_VALUE = "release_date.desc";

  @NonNull
  public static FilmController getInstance() {
    return FILM_CONTROLLER;
  }

  @NonNull
  public Single<List<Film>> getLatestFilms(int pageNumber) {
    return Single.zip(GenreController.getInstance().getAllGenres(),
        getLatestFilmsFromService(pageNumber).map(ListResponse::getResults),
        this::replaceGenreIdsForGenreNames);
  }

  @NonNull
  public Single<List<Film>> getThisYearsFilms(int pageNumber) {
    return Single.zip(GenreController.getInstance().getAllGenres(),
        getYearFilmsFromService(getCurrentYear(), pageNumber).map(ListResponse::getResults),
        this::replaceGenreIdsForGenreNames);
  }

  @NonNull
  public Single<List<Film>> getSpecificYearFilms(int pageNumber) {
    return Single.zip(GenreController.getInstance().getAllGenres(),
        getYearFilmsFromService(YEAR_1985, pageNumber).map(ListResponse::getResults),
        this::replaceGenreIdsForGenreNames);
  }

  @NonNull
  private Single<ListResponse<FilmResponse>> getLatestFilmsFromService(int pageNumber){
    FilmsService service = RestProvider.getInstance().provideFilmService();
    return service.getLatestFilms(SORT_BY_QUERY_VALUE, getTodaysDate(),
        null, null, null, pageNumber)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread());
  }

  @NonNull
  private Single<ListResponse<FilmResponse>> getYearFilmsFromService(int year, int pageNumber){
    FilmsService service = RestProvider.getInstance().provideFilmService();
    return service.getSpecificYearFilms(year, null, null, pageNumber)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread());
  }

  @NonNull
  private List<Film> replaceGenreIdsForGenreNames(@NonNull Map<Long, Genre> genreMap,
                                                  @NonNull List<FilmResponse> filmsResponse) {
    return Stream.of(filmsResponse)
        .map(response -> {
              Stream.of(response.getGenresIds())
                  .map(genreMap::get)
                  .forEach(response.getGenres()::add);
              return response;
            }
        )
        .collect(Collectors.<Film>toList());
  }

  @NonNull
  private String getTodaysDate(){
    Date todaysDate = new Date();
    return DATE_FORMATTER.format(todaysDate);
  }

  private int getCurrentYear(){
    return Calendar.getInstance().get(Calendar.YEAR);
  }
}
