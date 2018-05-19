package com.xmartlabs.moviefan.database.common;

import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;

import com.raizlabs.android.dbflow.config.DatabaseConfig;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowLog;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.xmartlabs.moviefan.BuildConfig;
import com.xmartlabs.moviefan.MovieFanApplication;
import com.xmartlabs.moviefan.database.MovieFanDatabase;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by bruno on 1/9/18.
 */
@Singleton
public class DatabaseManager {
  @NonNull
  private final FlowConfig flowConfig;

  @Inject
  public DatabaseManager() {
    flowConfig = new FlowConfig.Builder(MovieFanApplication.getContext())
        .addDatabaseConfig(new DatabaseConfig.Builder(MovieFanDatabase.class).build())
        .build();
  }

  public void initializeDatabase() {
    FlowManager.init(flowConfig);
    FlowLog.setMinimumLoggingLevel(BuildConfig.DEBUG ? FlowLog.Level.V : FlowLog.Level.WTF);
  }

  @WorkerThread
  public void resetDatabase() {
    FlowManager.getDatabase(MovieFanDatabase.class).reset(MovieFanApplication.getContext());
    FlowManager.destroy();
    initializeDatabase();
  }
}
