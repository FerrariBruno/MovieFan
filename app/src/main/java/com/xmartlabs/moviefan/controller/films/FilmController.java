package com.xmartlabs.moviefan.controller.films;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;

import com.annimon.stream.Optional;
import com.raizlabs.android.dbflow.sql.language.SQLOperator;
import com.xmartlabs.moviefan.controller.BaseController;
import com.xmartlabs.moviefan.controller.genres.GenreController;
import com.xmartlabs.moviefan.model.Film;
import com.xmartlabs.moviefan.model.Genre;

import org.threeten.bp.Year;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Single;

/**
 * Created by bruno on 12/21/17.
 */
public class FilmController extends BaseController<Long, Film, SQLOperator, FilmServiceController> {
  private static final int FIRST_PAGE = 1;
  private static final Year YEAR_1991 = Year.of(1991);

  @Inject
  GenreController genreController;

  public FilmController(@NonNull FilmDatabaseController entityProvider,
                        @NonNull FilmServiceController serviceProvider) {
    super(entityProvider, serviceProvider);
  }

  @CheckResult
  @NonNull
  public Flowable<List<Film>> getFirstPageOfLatestFilms(@NonNull Optional<Genre> genre, boolean adultContent) {
    return getEntities(getEntityServiceProvider()
          .getLatestFilms(FIRST_PAGE, genre, adultContent, genreController::getAllGenres));
  }

  @CheckResult
  @NonNull
  public Single<List<Film>> getLatestFilms(int pageNumber,
                                             @NonNull Optional<Genre> genre,
                                             boolean adultContent) {
    return getEntityServiceProvider().getLatestFilms(pageNumber, genre, adultContent, genreController::getAllGenres);
  }

  @CheckResult
  @NonNull
  public Single<List<Film>> getThisYearsFilms(int pageNumber,
                                              @NonNull Optional<Genre> genre,
                                              boolean adultContent) {
    return getEntityServiceProvider().getYearFilms
        (Year.now(), pageNumber, genre, genreController::getAllGenres, adultContent);
  }

  @CheckResult
  @NonNull
  public Single<List<Film>> getSpecificYearFilms(int pageNumber,
                                                 @NonNull Optional<Genre> genre,
                                                 boolean adultContent) {
    return getEntityServiceProvider().getYearFilms
        (YEAR_1991, pageNumber, genre, genreController::getAllGenres, adultContent);
  }
}
