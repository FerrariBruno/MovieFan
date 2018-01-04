package com.xmartlabs.moviefan.module;

import com.xmartlabs.bigbang.core.model.BuildInfo;
import com.xmartlabs.bigbang.core.module.SessionInterceptor;

import okhttp3.OkHttpClient;

public class OkHttpModule extends com.xmartlabs.bigbang.core.module.OkHttpModule {
  @Override
  public OkHttpClient provideServiceOkHttpClient(OkHttpClient.Builder clientBuilder, BuildInfo buildInfo) {
    clientBuilder
        .addInterceptor(new SessionInterceptor())
        .addInterceptor(new ApiKeyQueryInterceptor());
    return super.provideServiceOkHttpClient(clientBuilder, buildInfo);
  }
}
