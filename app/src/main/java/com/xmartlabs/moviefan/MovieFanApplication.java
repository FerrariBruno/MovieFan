package com.xmartlabs.moviefan;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.multidex.MultiDex;

import com.annimon.stream.Optional;
import com.jakewharton.threetenabp.AndroidThreeTen;
import com.tspoon.traceur.Traceur;
import com.tspoon.traceur.TraceurConfig;
import com.xmartlabs.bigbang.core.Injector;
import com.xmartlabs.bigbang.core.helper.GeneralErrorHelper;
import com.xmartlabs.bigbang.core.log.LoggerTree;
import com.xmartlabs.bigbang.core.model.BuildInfo;
import com.xmartlabs.bigbang.log.crashlytics.CrashlyticsLogger;
import com.xmartlabs.bigbang.retrofit.helper.ServiceErrorHandler;
import com.xmartlabs.moviefan.database.common.DatabaseManager;
import com.xmartlabs.moviefan.module.AndroidModule;
import com.xmartlabs.moviefan.module.OkHttpModule;
import com.xmartlabs.moviefan.module.RestServiceModule;
import com.xmartlabs.moviefan.module.ServiceGsonModule;

import javax.inject.Inject;

import bullet.ObjectGraph;
import timber.log.Timber;

public class MovieFanApplication extends Application {
  @Nullable
  private static MovieFanApplication instance;

  @Inject
  BuildInfo buildInfo;
  @Inject
  DatabaseManager databaseManager;
  @Inject
  GeneralErrorHelper generalErrorHelper;
  @Inject
  LoggerTree loggerTree;
  @Inject
  ServiceErrorHandler serviceErrorHandler;

  public MovieFanApplication() {
    instance = this;
  }

  @NonNull
  public static MovieFanApplication getContext() {
    return Optional.ofNullable(instance).get();
  }

  @Override
  protected void attachBaseContext(@NonNull Context base) {
    super.attachBaseContext(base);

    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP || !BuildConfig.DEBUG) {
      MultiDex.install(this);
    }
  }

  @Override
  public void onCreate() {
    super.onCreate();
    initializeThreeTenABP();
    initializeInjections();
    initializeDatabase();
    initializeRxErrorHandler();
    initializeLogging();
  }

  private void initializeInjections() {
    ApplicationComponent component = createComponent();
    ObjectGraph bullet = createBullet(component);
    Injector.getInstance().setObjectGraph(bullet);
    Injector.inject(this);
  }

  @NonNull
  protected ApplicationComponent createComponent() {
    return DaggerApplicationComponent.builder()
        .coreAndroidModule(new AndroidModule(this))
        .restServiceModule(new RestServiceModule())
        .okHttpModule(new OkHttpModule())
        .serviceGsonModule(new ServiceGsonModule())
        .build();
  }

  @NonNull
  protected ObjectGraph createBullet(ApplicationComponent component) {
    return new BulletApplicationComponent(component);
  }

  private void initializeDatabase() {
    databaseManager.initializeDatabase();
  }

  private void initializeThreeTenABP() {
    AndroidThreeTen.init(this);
  }

  private void initializeLogging() {
    loggerTree.addLogger(new CrashlyticsLogger().initialize(buildInfo, this));
    Timber.plant(loggerTree);
  }

  @SuppressWarnings("unchecked")
  private void initializeRxErrorHandler() {
    serviceErrorHandler.handleServiceErrors();

    TraceurConfig config = new TraceurConfig(
        true,
        BuildConfig.DEBUG ? Traceur.AssemblyLogLevel.SHOW_ALL : Traceur.AssemblyLogLevel.NONE,
        generalErrorHelper.getGeneralErrorAction()::accept);
    Traceur.enableLogging(config);
  }
}
