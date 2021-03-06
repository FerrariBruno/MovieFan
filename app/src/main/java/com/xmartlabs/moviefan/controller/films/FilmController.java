package com.xmartlabs.moviefan.controller.films;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.annimon.stream.Optional;
import com.xmartlabs.bigbang.core.controller.EntityDao;
import com.xmartlabs.moviefan.controller.BaseController;
import com.xmartlabs.moviefan.controller.genres.GenreController;
import com.xmartlabs.moviefan.model.Film;
import com.xmartlabs.moviefan.model.Genre;

import org.threeten.bp.Year;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Created by bruno on 12/21/17.
 */
//TODO change conditions with DB integration
public class FilmController extends BaseController<Long, Film, Void, FilmServiceController> {
  private static final Year YEAR_1991 = Year.of(1991);

  @Inject
  GenreController genreController;

  //TODO change nullability after DB integration
  public FilmController(@Nullable EntityDao<Long, Film, Void> entityDao, @NonNull FilmServiceController serviceProvider) {
    super(entityDao, serviceProvider);
  }

  @CheckResult
  @NonNull
  public Single<List<Film>> getLatestFilms(int pageNumber,
                                           @NonNull Optional<Genre> genre,
                                           boolean adultContent) {
    return getEntityServiceProvider().getLatestFilms(pageNumber, genre, genreController.getAllGenres(), adultContent);
  }

  @CheckResult
  @NonNull
  public Single<List<Film>> getThisYearsFilms(int pageNumber,
                                              @NonNull Optional<Genre> genre,
                                              boolean adultContent) {
    return getEntityServiceProvider().getYearFilms
        (Year.now(), pageNumber, genre, genreController.getAllGenres(), adultContent);
  }

  @CheckResult
  @NonNull
  public Single<List<Film>> getSpecificYearFilms(int pageNumber,
                                                 @NonNull Optional<Genre> genre,
                                                 boolean adultContent) {
    return getEntityServiceProvider().getYearFilms
        (YEAR_1991, pageNumber, genre, genreController.getAllGenres(), adultContent);
  }
}
