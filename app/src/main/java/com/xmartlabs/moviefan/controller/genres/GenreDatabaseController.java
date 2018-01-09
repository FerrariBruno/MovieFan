package com.xmartlabs.moviefan.controller.genres;

import com.raizlabs.android.dbflow.sql.language.property.Property;
import com.xmartlabs.moviefan.controller.BaseDatabaseController;
import com.xmartlabs.moviefan.model.Genre;

/**
 * Created by bruno on 1/9/18.
 */
public class GenreDatabaseController extends BaseDatabaseController<Long, Genre> {
  private static final int FIRST_ID = 0;

  public GenreDatabaseController(Class<Genre> modelClass, Property<Long> propertyId) {
    super(modelClass, propertyId);
  }
}
