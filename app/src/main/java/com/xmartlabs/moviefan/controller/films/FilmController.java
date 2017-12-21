package com.xmartlabs.moviefan.controller.films;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;

import com.annimon.stream.Optional;
import com.xmartlabs.bigbang.core.controller.EntityDao;
import com.xmartlabs.moviefan.controller.BaseController;
import com.xmartlabs.moviefan.controller.genres.GenreController;
import com.xmartlabs.moviefan.helper.DateHelper;
import com.xmartlabs.moviefan.model.Film;
import com.xmartlabs.moviefan.model.Genre;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Created by bruno on 12/21/17.
 */
//TODO change conditions with DB integration
public class FilmController extends BaseController<Long, Film, Void, FilmServiceController> {
  private static final int YEAR_1991 = 1991;

  @Inject
  GenreController genreController;

  public FilmController(EntityDao<Long, Film, Void> entityDao, FilmServiceController serviceProvider) {
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
        (DateHelper.getCurrentYear(), pageNumber, genre, genreController.getAllGenres(), adultContent);
  }

  @CheckResult
  @NonNull
  public Single<List<Film>> getSpecificYearFilms(int pageNumber,
                                                 @NonNull Single<Map<Long, Genre>> genres,
                                                 @NonNull Optional<Genre> genre,
                                                 boolean adultContent) {
    return getEntityServiceProvider().getYearFilms
        (YEAR_1991, pageNumber, genre, genreController.getAllGenres(), adultContent);
  }
}
