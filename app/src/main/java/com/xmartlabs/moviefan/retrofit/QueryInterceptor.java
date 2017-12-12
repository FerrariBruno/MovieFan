package com.xmartlabs.moviefan.retrofit;

import android.support.annotation.NonNull;

import com.xmartlabs.moviefan.MovieFanApplication;
import com.xmartlabs.moviefan.R;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by bruno on 12/11/17.
 */
public class QueryInterceptor implements Interceptor {
  @Override
  public Response intercept(@NonNull Chain chain) throws IOException {
    Request request = chain.request();
    HttpUrl url = request.url().newBuilder().addQueryParameter("api_key", MovieFanApplication.getContext()
        .getString(R.string.api_key)).build();
    request = request.newBuilder()
        .url(url)
        .build();
    return chain.proceed(request);
  }
}
