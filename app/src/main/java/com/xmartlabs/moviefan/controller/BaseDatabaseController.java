package com.xmartlabs.moviefan.controller;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;

import com.annimon.stream.ComparatorCompat;
import com.raizlabs.android.dbflow.sql.language.SQLOperator;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.sql.language.property.Property;
import com.xmartlabs.bigbang.core.helper.function.Function;
import com.xmartlabs.bigbang.core.model.EntityWithId;
import com.xmartlabs.bigbang.dbflow.controller.DbFlowController;
import com.xmartlabs.moviefan.database.MovieFanDatabase;
import com.xmartlabs.moviefan.model.common.BaseModel;

import org.threeten.bp.LocalDateTime;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by bruno on 1/9/18.
 */
public class BaseDatabaseController<Id, D extends BaseModel & EntityWithId<Id>> extends DbFlowController<Id, D> {
  private final Class<D> modelClass;

  public BaseDatabaseController(Class<D> modelClass, Property<Id> propertyId) {
    super(modelClass, propertyId);
    this.modelClass = modelClass;
  }

  @Override
  protected Class<?> getAppDataBaseClass() {
    return MovieFanDatabase.class;
  }

  @CheckResult
  @NonNull
  @Override
  public Single<D> createEntity(@NonNull D entity) {
    LocalDateTime creationTime = LocalDateTime.now();
    entity.setCreateDate(creationTime);
    entity.setModifiedDate(creationTime);
    return super.createEntity(entity)
        .observeOn(Schedulers.io())
        .map(this::loadRelatedEntities)
        .compose(applySingleIoSchedulers());
  }

  @CheckResult
  @NonNull
  @Override
  public Single<D> updateEntity(@NonNull D entity) {
    entity.setModifiedDate(LocalDateTime.now());
    return super.updateEntity(entity)
        .observeOn(Schedulers.io())
        .map(this::loadRelatedEntities)
        .compose(applySingleIoSchedulers());
  }

  @CheckResult
  @NonNull
  @Override
  public Maybe<D> getEntity(@NonNull Id id) {
    return super.getEntity(id)
        .observeOn(Schedulers.io())
        .map(this::loadRelatedEntities)
        .compose(applyMaybeIoSchedulers());
  }

  @NonNull
  @Override
  public Single<List<D>> getEntities(@NonNull SQLOperator... conditions) {
    return super.getEntities(conditions)
        .observeOn(Schedulers.io())
        .flatMapObservable(Observable::fromIterable)
        .map(this::loadRelatedEntities)
        .toList()
        .compose(applySingleIoSchedulers());
  }

  @CheckResult
  @NonNull
  protected Single<Id> getNextTemporaryId(@NonNull ComparatorCompat<D> comparator,
                                          @NonNull Function<D, Id> getIdValueFunction,
                                          @NonNull Id defaultValue,
                                          @NonNull Function<Id, Id> incrementFunction) {
    return getEntities()
        .compose(applySingleIoSchedulers())
        .toObservable()
        .flatMap(Observable::fromIterable)
        .sorted(comparator.reversed())
        .firstElement()
        .map(getIdValueFunction::apply)
        .defaultIfEmpty(defaultValue)
        .map(incrementFunction::apply)
        .toSingle()
        .compose(applySingleIoSchedulers());
  }

  @CheckResult
  @NonNull
  @WorkerThread
  public D loadRelatedEntities(@NonNull D entity) {
    return entity;
  }

  @CheckResult
  @NonNull
  public Completable deleteEntities(@NonNull SQLOperator... conditions) {
    return Completable.fromAction(() -> SQLite.delete()
        .from(modelClass)
        .where(conditions)
        .execute())
        .compose(applyCompletableIoSchedulers());
  }
}
