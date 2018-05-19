package com.xmartlabs.moviefan.controller.genres;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;

import com.raizlabs.android.dbflow.sql.language.SQLOperator;
import com.xmartlabs.moviefan.controller.BaseController;
import com.xmartlabs.moviefan.model.Genre;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by bruno on 12/21/17.
 */
public class GenreController extends BaseController<Long, Genre, SQLOperator, GenreServiceController> {
  public GenreController(@NonNull GenreDatabaseController entityProvider,
                         @NonNull GenreServiceController serviceController) {
    super(entityProvider, serviceController);
  }

  @NonNull
  private Single<List<Genre>> getGenresFromService = getEntities(getEntityServiceProvider().getGenresFromService())
      .share()
      .lastOrError();

  @CheckResult
  @NonNull
  public Single<Map<Long, Genre>> getAllGenres(boolean forceReload) {
    return Single
        .defer(() -> forceReload
            ? getGenresFromService
            : getEntityDao().getEntities())
        .compose(applySingleIoSchedulers())
        .flatMapObservable(Observable::fromIterable)
        .toMap(Genre::getId);
  }
}
