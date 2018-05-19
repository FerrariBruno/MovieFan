package com.xmartlabs.moviefan.controller;

import com.raizlabs.android.dbflow.sql.language.property.Property;
import com.xmartlabs.bigbang.core.model.EntityWithId;
import com.xmartlabs.bigbang.dbflow.controller.DbFlowController;
import com.xmartlabs.moviefan.database.MovieFanDatabase;
import com.xmartlabs.moviefan.model.common.BaseModel;

import lombok.Getter;

/**
 * Created by bruno on 1/9/18.
 */
public class BaseDatabaseController<Id, D extends BaseModel & EntityWithId<Id>> extends DbFlowController<Id, D> {
  @Getter
  private final Class<D> modelClass;

  public BaseDatabaseController(Class<D> modelClass, Property<Id> propertyId) {
    super(modelClass, propertyId);
    this.modelClass = modelClass;
  }

  @Override
  protected Class<?> getAppDataBaseClass() {
    return MovieFanDatabase.class;
  }
}
