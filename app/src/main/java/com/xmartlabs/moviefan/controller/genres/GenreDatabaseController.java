package com.xmartlabs.moviefan.controller.genres;

import com.xmartlabs.moviefan.controller.BaseDatabaseController;
import com.xmartlabs.moviefan.model.Genre;
import com.xmartlabs.moviefan.model.Genre_Table;

/**
 * Created by bruno on 1/9/18.
 */
public class GenreDatabaseController extends BaseDatabaseController<Long, Genre> {
  public GenreDatabaseController() {
    super(Genre.class, Genre_Table.id);
  }
}
