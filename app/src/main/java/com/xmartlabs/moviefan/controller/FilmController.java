package com.xmartlabs.moviefan.controller;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.xmartlabs.moviefan.MovieFanApplication;
import com.xmartlabs.moviefan.R;
import com.xmartlabs.moviefan.helper.DateHelper;
import com.xmartlabs.moviefan.retrofit.RestProvider;
import com.xmartlabs.moviefan.services.FilmsService;
import com.xmartlabs.moviefan.ui.models.Film;
import com.xmartlabs.moviefan.ui.models.FilmResponse;
import com.xmartlabs.moviefan.ui.models.Genre;
import com.xmartlabs.moviefan.ui.models.ListResponse;

import java.util.List;
import java.util.Map;

import io.reactivex.Single;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Created by bruno on 12/6/17.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FilmController extends BaseController {
  private final int YEAR_1985 = 1985;
  private final String SORT_BY_QUERY_VALUE = "release_date.desc";
  private static final FilmController FILM_CONTROLLER = new FilmController();

  @NonNull
  public static FilmController getInstance() {
    return FILM_CONTROLLER;
  }

  @CheckResult
  @NonNull
  public Single<List<Film>> getLatestFilms(int pageNumber) {
    return Single
        .zip(
            GenreController.getInstance().getAllGenres(),
            getLatestFilmsFromService(pageNumber).map(ListResponse::getResults),
            this::updateFilmsWithGenresAndImageUrl)
        .compose(applySingleIoSchedulers());
  }

  @CheckResult
  @NonNull
  public Single<List<Film>> getThisYearsFilms(int pageNumber) {
    return Single
        .zip(
            GenreController.getInstance().getAllGenres(),
            getYearFilmsFromService(DateHelper.getCurrentYear(), pageNumber).map(ListResponse::getResults),
            this::updateFilmsWithGenresAndImageUrl)
        .compose(applySingleIoSchedulers());
  }

  @CheckResult
  @NonNull
  public Single<List<Film>> getSpecificYearFilms(int pageNumber) {
    return Single
        .zip(
            GenreController.getInstance().getAllGenres(),
            getYearFilmsFromService(YEAR_1985, pageNumber).map(ListResponse::getResults),
            this::updateFilmsWithGenresAndImageUrl)
        .compose(applySingleIoSchedulers());
  }

  @NonNull
  private Single<ListResponse<FilmResponse>> getLatestFilmsFromService(int pageNumber){
    FilmsService service = RestProvider.getInstance().provideFilmService();
    //TODO replace null values with values from filter view
    return service.getLatestFilms(SORT_BY_QUERY_VALUE, DateHelper.getTodaysDate(),
        null, null, null, pageNumber)
        .compose(applySingleIoSchedulers());
  }

  @CheckResult
  @NonNull
  @WorkerThread
  private Single<ListResponse<FilmResponse>> getYearFilmsFromService(int year, int pageNumber){
    FilmsService service = RestProvider.getInstance().provideFilmService();
    //TODO replace null values with values from filter view
    return service.getSpecificYearFilms(year, null, null, pageNumber)
        .compose(applySingleIoSchedulers());
  }

  @NonNull
  private List<Film> updateFilmsWithGenresAndImageUrl(@NonNull Map<Long, Genre> genreMap,
                                                      @NonNull List<FilmResponse> filmsResponse) {
    return Stream.of(filmsResponse)
        .map(response -> {
          response.setPosterPath(MovieFanApplication.getContext().getString(R.string.base_url_image) + response.getPosterPath());
          List<Genre> genres = Stream.of(response.getGenresIds())
              .map(genreMap::get)
              .toList();
          response.setGenres(genres);
          return response;
        })
        .collect(Collectors.<Film>toList());
  }
}
