package com.xmartlabs.moviefan.model;

import android.support.annotation.Nullable;

import com.xmartlabs.bigbang.core.model.SessionType;
import com.xmartlabs.moviefan.MovieFanApplication;
import com.xmartlabs.moviefan.R;

import lombok.Getter;

public class Session implements SessionType {
  @Getter
  @Nullable
  private String accessToken;

  @Override
  public void setAccessToken(@Nullable String accessToken) {
    accessToken = MovieFanApplication.getContext().getString(R.string.access_token);
  }
}
