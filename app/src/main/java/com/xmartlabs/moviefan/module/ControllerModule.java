package com.xmartlabs.moviefan.module;

import android.support.annotation.NonNull;

import com.xmartlabs.bigbang.core.controller.CoreSessionController;
import com.xmartlabs.moviefan.controller.SessionController;
import com.xmartlabs.moviefan.controller.films.FilmController;
import com.xmartlabs.moviefan.controller.films.FilmServiceController;
import com.xmartlabs.moviefan.controller.genres.GenreController;
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
  FilmController providerFilmController(@NonNull FilmServiceController filmServiceController) {
    //TODO add entity provider when DB integration comes
    return new FilmController(null, filmServiceController);
  }

  @Provides
  @Singleton
  FilmServiceController provideFilmServiceController() {
    return new FilmServiceController();
  }

  @Provides
  @Singleton
  GenreController provideGenreController(@NonNull GenreServiceController genreServiceController) {
    //TODO add entity provider when DB integration comes
    return new GenreController(null, genreServiceController);
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
