package com.xmartlabs.moviefan.module;

import android.support.annotation.NonNull;

import com.xmartlabs.bigbang.core.controller.CoreSessionController;
import com.xmartlabs.moviefan.controller.SessionController;
import com.xmartlabs.moviefan.controller.films.FilmController;
import com.xmartlabs.moviefan.controller.films.FilmDatabaseController;
import com.xmartlabs.moviefan.controller.films.FilmServiceController;
import com.xmartlabs.moviefan.controller.genres.GenreController;
import com.xmartlabs.moviefan.controller.genres.GenreDatabaseController;
import com.xmartlabs.moviefan.controller.genres.GenreServiceController;
import com.xmartlabs.moviefan.model.Session;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ControllerModule {
  @Provides
  @Singleton
  CoreSessionController provideCoreSessionController(SessionController sessionController) {
    return sessionController;
  }

  @Provides
  @Singleton
  FilmController provideFilmController(@NonNull FilmDatabaseController filmDatabaseController,
                                       @NonNull FilmServiceController filmServiceController) {
    return new FilmController(filmDatabaseController, filmServiceController);
  }

  @Provides
  @Singleton
  FilmDatabaseController provideFilmDatabaseController() {
    return new FilmDatabaseController();
  }

  @Provides
  @Singleton
  FilmServiceController provideFilmServiceController() {
    return new FilmServiceController();
  }

  @Provides
  @Singleton
  GenreController provideGenreController(@NonNull GenreDatabaseController genreDatabaseController,
                                         @NonNull GenreServiceController genreServiceController) {
    return new GenreController(genreDatabaseController, genreServiceController);
  }

  @Provides
  @Singleton
  GenreDatabaseController provideGenreDatabaseController() {
    return new GenreDatabaseController();
  }

  @Provides
  @Singleton
  GenreServiceController provideGenreServiceController() {
    return new GenreServiceController();
  }

  @Provides
  @Singleton
  SessionController provideSessionController() {
    return new SessionController(Session.class);
  }
}
