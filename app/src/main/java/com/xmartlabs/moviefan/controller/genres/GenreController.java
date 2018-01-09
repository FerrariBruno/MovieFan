package com.xmartlabs.moviefan.controller.genres;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.xmartlabs.moviefan.controller.BaseController;
import com.xmartlabs.moviefan.model.Genre;

import java.util.Map;

import io.reactivex.Single;
import lombok.Getter;

/**
 * Created by bruno on 12/21/17.
 */
//TODO change conditions with DB integration
public class GenreController extends BaseController<Long, Genre, Void, GenreServiceController> {
  @Getter
  @Nullable
  private Map<Long, Genre> genres;

  public GenreController(@NonNull GenreDatabaseController genreDatabaseController,
                         @NonNull GenreServiceController serviceController) {
    super(genreDatabaseController, serviceController);
  }

  @Nullable
  private Single<Map<Long, Genre>> getGenresFromService = getEntityServiceProvider()
      .getGenresFromService()
      .doOnSuccess(genres -> this.genres = genres)
      .toObservable()
      .share()
      .firstOrError();

  @CheckResult
  @NonNull
  public Single<Map<Long, Genre>> getAllGenres() {
    return Single
        .defer(() -> genres == null
            ? getGenresFromService
            : Single.just(genres))
        .compose(applySingleIoSchedulers());
  }
}
