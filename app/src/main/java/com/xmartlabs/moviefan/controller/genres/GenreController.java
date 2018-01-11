package com.xmartlabs.moviefan.controller.genres;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.raizlabs.android.dbflow.sql.language.SQLOperator;
import com.xmartlabs.moviefan.controller.BaseController;
import com.xmartlabs.moviefan.model.Genre;

import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.Single;
import lombok.Getter;

/**
 * Created by bruno on 12/21/17.
 */
public class GenreController extends BaseController<Long, Genre, SQLOperator, GenreServiceController> {
  @Getter
  @Nullable
  private Map<Long, Genre> genres;

  public GenreController(@NonNull GenreDatabaseController genreDatabaseController,
                         @NonNull GenreServiceController serviceController) {
    super(genreDatabaseController, serviceController);
  }

  @NonNull
  private Single<List<Genre>> getGenresFromService = getEntityServiceProvider()
      .getGenresFromService();

  @CheckResult
  @NonNull
  public Flowable<Map<Long, Genre>> getAllGenres() {
    return Flowable
        .defer(() -> genres == null
            ? getEntities(getGenresFromService)
                .toMap()
                .doOnSuccess(genres -> this.genres = genres)
                .toFlowable()
                .share()
                .firstOrError()
            : Flowable.just(genres))
        .compose(applySingleIoSchedulers());
  }
}
