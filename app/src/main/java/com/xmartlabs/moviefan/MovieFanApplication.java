package com.xmartlabs.moviefan;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;
import timber.log.Timber;

/**
 * Created by bruno on 12/11/17.
 */
public class MovieFanApplication extends Application {
  private static MovieFanApplication instance;

  @Override
  public void onCreate() {
    super.onCreate();
    Fabric.with(this, new Crashlytics());
    instance = this;
    initializeLogging();
  }

  private void initializeLogging(){
    Timber.plant(new Timber.DebugTree());
  }

  @NonNull
  public static Context getContext() {
    return instance.getApplicationContext();
  }
}
