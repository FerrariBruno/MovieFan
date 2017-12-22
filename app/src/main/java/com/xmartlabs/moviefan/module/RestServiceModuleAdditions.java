package com.xmartlabs.moviefan.module;

import com.xmartlabs.bigbang.core.providers.AccessTokenProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RestServiceModuleAdditions {
  @Provides
  @Singleton
  AccessTokenProvider provideAccessTokenProvider() {
    return new AccessTokenProvider();
  }
}
