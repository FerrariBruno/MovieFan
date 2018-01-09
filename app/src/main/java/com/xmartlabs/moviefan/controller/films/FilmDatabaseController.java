package com.xmartlabs.moviefan.controller.films;

import com.raizlabs.android.dbflow.sql.language.property.Property;
import com.xmartlabs.moviefan.controller.BaseDatabaseController;
import com.xmartlabs.moviefan.model.Film;

/**
 * Created by bruno on 1/9/18.
 */
public class FilmDatabaseController extends BaseDatabaseController<Long, Film> {
  private static final int FIRST_ID = 0;

  public FilmDatabaseController(Class<Film> modelClass, Property<Long> propertyId) {
    super(modelClass, propertyId);
  }
}
