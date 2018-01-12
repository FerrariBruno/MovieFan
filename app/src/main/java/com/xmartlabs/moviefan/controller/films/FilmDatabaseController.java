package com.xmartlabs.moviefan.controller.films;

import com.xmartlabs.moviefan.controller.BaseDatabaseController;
import com.xmartlabs.moviefan.model.Film;
import com.xmartlabs.moviefan.model.Film_Table;

/**
 * Created by bruno on 1/9/18.
 */
public class FilmDatabaseController extends BaseDatabaseController<Long, Film> {
  public FilmDatabaseController() {
    super(Film.class, Film_Table.id);
  }
}
