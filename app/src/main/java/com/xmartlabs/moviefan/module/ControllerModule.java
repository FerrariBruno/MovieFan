package com.xmartlabs.moviefan.module;

import com.xmartlabs.bigbang.core.controller.CoreSessionController;
import com.xmartlabs.moviefan.controller.AuthController;
import com.xmartlabs.moviefan.controller.SessionController;
import com.xmartlabs.moviefan.model.Session;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ControllerModule {
  @Provides
  @Singleton
  AuthController provideAuthController() {
    return new AuthController();
  }

  @Provides
  @Singleton
  CoreSessionController provideCoreSessionController(SessionController sessionController) {
    return sessionController;
  }

  @Provides
  @Singleton
  SessionController provideSessionController() {
    return new SessionController(Session.class);
  }
}
