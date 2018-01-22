package com.xmartlabs.moviefan.controller.films;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.annimon.stream.Stream;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLOperator;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.BaseModel;
import com.xmartlabs.moviefan.controller.BaseDatabaseController;
import com.xmartlabs.moviefan.model.Film;
import com.xmartlabs.moviefan.model.Film_Genre;
import com.xmartlabs.moviefan.model.Film_Genre_Table;
import com.xmartlabs.moviefan.model.Film_Table;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by bruno on 1/9/18.
 */
public class FilmDatabaseController extends BaseDatabaseController<Long, Film> {
  public FilmDatabaseController() {
    super(Film.class, Film_Table.id);
  }

  @CheckResult
  @NonNull
  @Override
  public Single<List<Film>> deleteAndInsertEntities(@Nullable List<Film> entitiesToInsert,
                                                    @NonNull SQLOperator... conditionsToDelete) {
    return Single
        .fromCallable(() -> {
          FlowManager.getDatabase(getAppDataBaseClass())
              .executeTransaction(databaseWrapper -> {
                SQLite.delete(getModelClass())
                    .where(conditionsToDelete)
                    .execute();
                Stream.ofNullable(entitiesToInsert)
                    .forEach(this::deleteGenresForFilm);
                Stream.ofNullable(entitiesToInsert)
                    .forEach(this::saveGenresForFilm);
                Stream.ofNullable(entitiesToInsert)
                    .forEach(BaseModel::save);
              });
          return entitiesToInsert;
        })
        .subscribeOn(Schedulers.io());
  }

  private void deleteGenresForFilm(@NonNull Film film) {
    SQLite.delete()
        .from(Film_Genre.class)
        .where(Film_Genre_Table.film_id.is(film.getId()))
        .execute();
  }

  private void saveGenresForFilm(@NonNull Film film) {
    Stream.ofNullable(film.getGenres())
        .forEach(genre -> {
          Film_Genre film_genre = new Film_Genre();
          film_genre.setFilm(film);
          film_genre.setGenre(genre);
          film_genre.save();
        });
  }

  @CheckResult
  @NonNull
  @Override
  public Single<List<Film>> getEntities(@NonNull SQLOperator... conditions) {
    return Single
        .fromCallable(() -> {
          List<Film> films = SQLite.select()
              .from(getModelClass())
              .where(conditions)
              .queryList();
          loadGenresForFilms(films);
          return films;
        })
        .subscribeOn(Schedulers.io());
  }

  private void loadGenresForFilms(@Nullable List<Film> films) {
    Stream.ofNullable(films)
        .forEach(film -> {
          List<Film_Genre> genresForFilm = SQLite.select()
              .from(Film_Genre.class)
              .where(Film_Genre_Table.film_id.is(film.getId()))
              .queryList();
          film.setGenres(Stream.ofNullable(genresForFilm)
              .map(Film_Genre::getGenre)
              .toList());
        });
  }
}
