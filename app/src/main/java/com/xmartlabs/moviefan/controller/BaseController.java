package com.xmartlabs.moviefan.controller;

import android.support.annotation.NonNull;

import com.xmartlabs.bigbang.core.controller.EntityController;
import com.xmartlabs.bigbang.core.controller.EntityDao;
import com.xmartlabs.bigbang.core.controller.EntityServiceProvider;
import com.xmartlabs.bigbang.core.model.EntityWithId;

/**
 * Created by bruno on 12/21/17.
 */
public abstract class BaseController<Id, E extends EntityWithId<Id>, Condition, S extends EntityServiceProvider<Id, E>>
    extends EntityController<Id, E, Condition, S> {
  public BaseController(@NonNull EntityDao<Id, E, Condition> entityDao, @NonNull S entityServiceProvider) {
    super(entityDao, entityServiceProvider);
  }
}
