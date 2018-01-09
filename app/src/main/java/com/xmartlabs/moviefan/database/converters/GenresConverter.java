package com.xmartlabs.moviefan.database.converters;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.raizlabs.android.dbflow.converter.TypeConverter;
import com.xmartlabs.bigbang.core.Injector;
import com.xmartlabs.moviefan.model.Genre;

import javax.inject.Inject;

/**
 * Created by bruno on 1/9/18.
 */
@com.raizlabs.android.dbflow.annotation.TypeConverter
public class GenresConverter extends TypeConverter<String, Genre[]> {
  @Inject
  Gson gson;

  public GenresConverter() {
    Injector.inject(this);
  }

  @Override
  public String getDBValue(@NonNull Genre[] genres) {
    return gson.toJson(genres, Genre[].class);
  }

  @Override
  public Genre[] getModelValue(@NonNull String data) {
    return gson.fromJson(data, Genre[].class);
  }
}
