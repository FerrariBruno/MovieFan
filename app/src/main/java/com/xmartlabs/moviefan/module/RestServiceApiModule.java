package com.xmartlabs.moviefan.module;

import android.support.annotation.NonNull;

import com.xmartlabs.moviefan.service.FilmsService;
import com.xmartlabs.moviefan.service.GenresService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by bruno on 12/21/17.
 */
@Module
public class RestServiceApiModule {
  @Provides
  @Singleton
  FilmsService provideFilmsService(@NonNull Retrofit retrofit) {
    return retrofit.create(FilmsService.class);
  }

  @Provides
  @Singleton
  GenresService provideGenresService(@NonNull Retrofit retrofit) {
    return retrofit.create(GenresService.class);
  }
}
