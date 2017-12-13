package com.xmartlabs.moviefan.ui.models;

import com.xmartlabs.moviefan.MovieFanApplication;
import com.xmartlabs.moviefan.R;

import lombok.Getter;
import lombok.NonNull;

/**
 * Created by bruno on 12/8/17.
 */
public class Session {
  @Getter
  @NonNull
  private final String accessToken = MovieFanApplication.getContext().getString(R.string.access_token);
}
