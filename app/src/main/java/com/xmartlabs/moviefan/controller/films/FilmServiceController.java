package com.xmartlabs.moviefan.controller.films;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;
import android.support.v4.util.Pair;

import com.annimon.stream.Optional;
import com.annimon.stream.Stream;
import com.xmartlabs.bigbang.core.helper.function.Function;
import com.xmartlabs.moviefan.MovieFanApplication;
import com.xmartlabs.moviefan.R;
import com.xmartlabs.moviefan.controller.BaseServiceController;
import com.xmartlabs.moviefan.model.Film;
import com.xmartlabs.moviefan.model.Genre;
import com.xmartlabs.moviefan.model.service.response.FilmResponse;
import com.xmartlabs.moviefan.model.service.response.ListResponse;
import com.xmartlabs.moviefan.service.FilmsService;

import org.threeten.bp.LocalDate;
import org.threeten.bp.Year;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by bruno on 12/21/17.
 */
public class FilmServiceController extends BaseServiceController<Long, Film> {
  private static final String SORT_BY_QUERY_VALUE = "release_date.desc";

  @Inject
  FilmsService filmsService;

  @CheckResult
  @NonNull
  @WorkerThread
  Single<List<Film>> getLatestFilms(int pageNumber, @NonNull Optional<Genre> genre, boolean adultContent,
                                    @NonNull Function<Boolean, Single<Map<Long, Genre>>> genresRequest) {
    return Single
        .zip(
            genresRequest.apply(false),
            getLatestFilmsFromService(pageNumber, getIdFromGenreOptional(genre), adultContent)
                .map(ListResponse::getResults),
            Pair::new)
        .compose(applySingleIoSchedulers())
        .flatMap(pair -> {
          Single<Map<Long, Genre>> genreMapSingle = checkIfGenresNeedRefreshing(pair)
              ? genresRequest.apply(true)
              : Single.just(pair.first);
          return  genreMapSingle
              .flatMap(genreMap -> updateFilmsWithGenresAndImageUrl(genreMap, pair.second));
        });
  }

  @NonNull
  private Single<ListResponse<FilmResponse>> getLatestFilmsFromService(int pageNumber,
                                                                       @NonNull String genreId,
                                                                       boolean adultContent) {
    return filmsService.getLatestFilms(SORT_BY_QUERY_VALUE, LocalDate.now(),
        adultContent, genreId, pageNumber)
        .compose(applySingleIoSchedulers());
  }

  @CheckResult
  @NonNull
  @WorkerThread
  Single<List<Film>> getYearFilms(@NonNull Year year,
                                  int pageNumber,
                                  @NonNull Optional<Genre> genre,
                                  @NonNull Function<Boolean, Single<Map<Long, Genre>>> genresRequest,
                                  boolean adultContent) {
    return Single
        .zip(
            genresRequest.apply(false),
            getYearFilmsFromService(year, pageNumber, getIdFromGenreOptional(genre), adultContent)
                .map(ListResponse::getResults),
            Pair::new)
        .compose(applySingleIoSchedulers())
        .flatMap(pair -> {
          Single<Map<Long, Genre>> genreMapSingle = checkIfGenresNeedRefreshing(pair)
              ? genresRequest.apply(true)
              : Single.just(pair.first);
          return  genreMapSingle
              .flatMap(genreMap -> updateFilmsWithGenresAndImageUrl(genreMap, pair.second));
        });
  }

  @WorkerThread
  private boolean checkIfGenresNeedRefreshing(Pair<Map<Long, Genre>, List<FilmResponse>> pair) {
    return Stream.of(pair.second)
        .map(FilmResponse::getGenresIds)
        .flatMap(Stream::of)
        .distinct()
        .anyMatch(genreId -> !pair.first.containsKey(genreId));
  }

  @NonNull
  private Single<ListResponse<FilmResponse>> getYearFilmsFromService(@NonNull Year year,
                                                                     int pageNumber,
                                                                     @NonNull String genreId,
                                                                     boolean adultContent) {
    return filmsService.getSpecificYearFilms(year, adultContent, genreId, pageNumber)
        .compose(applySingleIoSchedulers());
  }

  @NonNull
  private Single<List<Film>> updateFilmsWithGenresAndImageUrl(@NonNull Map<Long, Genre> genreMap,
                                                      @NonNull List<FilmResponse> filmsResponse) {
    return Observable.fromIterable(filmsResponse)
        .map(response -> {
          response.setPosterPath(MovieFanApplication.getContext().getString(R.string.base_url_image) + response.getPosterPath());
          List<Genre> genres = Stream.of(response.getGenresIds())
              .map(genreMap::get)
              .toList();
          response.setGenresFromList(genres);
          return response;
        })
        .cast(Film.class)
        .toList();
  }

  @NonNull
  private String getIdFromGenreOptional(@NonNull Optional<Genre> genre) {
    return genre.map(Genre::getId)
        .map(String::valueOf)
        .orElse("");
  }
}
