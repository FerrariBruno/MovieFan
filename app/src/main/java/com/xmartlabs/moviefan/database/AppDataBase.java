package com.xmartlabs.moviefan.database;

import com.raizlabs.android.dbflow.annotation.Database;

@Database(name = AppDataBase.NAME, version = AppDataBase.VERSION)
public class AppDataBase {
  public static final String NAME = "moviefan_database";
  public static final int VERSION = 1;
}
