package com.xmartlabs.moviefan.module;

import android.support.annotation.NonNull;

import com.xmartlabs.moviefan.MovieFanApplication;
import com.xmartlabs.moviefan.R;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by bruno on 1/4/18.
 */
public class ApiKeyQueryInterceptor implements Interceptor {
  private static final String API_KEY_QUERY_NAME = "api_key";
  private static final String API_KEY_VALUE = MovieFanApplication.getContext().getString(R.string.api_key);

  @NonNull
  @Override
  public Response intercept(@NonNull Chain chain) throws IOException {
    Request request = chain.request();

    HttpUrl url = request.url().newBuilder()
        .addQueryParameter(API_KEY_QUERY_NAME, API_KEY_VALUE)
        .build();
    request = request.newBuilder()
        .url(url)
        .build();
    return chain.proceed(request);
  }
}
