package com.xmartlabs.moviefan.module;

import android.content.Context;

import com.xmartlabs.moviefan.MovieFanApplication;
import com.xmartlabs.moviefan.R;

import okhttp3.HttpUrl;

public class RestServiceModule extends com.xmartlabs.bigbang.retrofit.module.RestServiceModule {
  private static final String BASE_URL = MovieFanApplication.getContext().getResources().getString(R.string.base_url);

  @Override
  public HttpUrl provideBaseUrl(Context context) {
    return HttpUrl.parse(BASE_URL);
  }
}
