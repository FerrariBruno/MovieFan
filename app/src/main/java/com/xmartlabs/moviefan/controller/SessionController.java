package com.xmartlabs.moviefan.controller;

import com.xmartlabs.moviefan.ui.models.Session;

import lombok.Getter;
import lombok.NonNull;

/**
 * Created by bruno on 12/8/17.
 */
public class SessionController extends BaseController {
  @Getter
  @NonNull
  private static Session currentSession = new Session();

  @NonNull
  public static String getAccessToken() {
    return getCurrentSession().getAccessToken();
  }
}
