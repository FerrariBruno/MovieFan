package com.xmartlabs.moviefan.database;

import com.raizlabs.android.dbflow.annotation.Database;

@Database(name = MovieFanDatabase.NAME, version = MovieFanDatabase.VERSION)
public class MovieFanDatabase {
  public static final String NAME = "moviefan";
  public static final int VERSION = 1;
}
