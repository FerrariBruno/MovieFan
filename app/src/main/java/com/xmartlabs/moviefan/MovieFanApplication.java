package com.xmartlabs.moviefan;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

/**
 * Created by bruno on 12/11/17.
 */
public class MovieFanApplication extends Application {
  private static MovieFanApplication instance;

  @NonNull
  public static MovieFanApplication getInstance() {
    return instance;
  }

  @Override
  public void onCreate() {
    super.onCreate();
    instance = this;
  }

  @NonNull
  public static Context getContext() {
    return instance.getApplicationContext();
  }
}
