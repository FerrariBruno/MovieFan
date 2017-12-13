package com.xmartlabs.moviefan.retrofit;

import android.support.annotation.NonNull;

import com.xmartlabs.moviefan.controller.SessionController;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by bruno on 12/8/17.
 */
public class SessionInterceptor implements Interceptor {
  private final static String AUTHORIZATION_HEADER_NAME = "Authorization";
  private final static String AUTHORIZATION_HEADER_VALUE = "Bearer ";

  @NonNull
  @Override
  public Response intercept(@NonNull Chain chain) throws IOException {
    Request request = chain.request();
    request = request.newBuilder()
        .addHeader(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE + SessionController.getAccessToken())
        .build();
    return chain.proceed(request);
  }
}
