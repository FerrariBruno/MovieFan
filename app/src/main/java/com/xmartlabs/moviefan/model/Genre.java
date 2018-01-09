package com.xmartlabs.moviefan.model;

import android.support.annotation.NonNull;

import com.raizlabs.android.dbflow.annotation.Table;
import com.xmartlabs.bigbang.core.model.EntityWithId;
import com.xmartlabs.moviefan.database.MovieFanDatabase;
import com.xmartlabs.moviefan.model.common.BaseModel;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by bruno on 12/21/17.
 */
@Builder
@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@EqualsAndHashCode(of = "id", callSuper = false)
@Table(database = MovieFanDatabase.class, cachingEnabled = true)
public class Genre extends BaseModel implements EntityWithId<Long> {
  private Long id;
  @NonNull
  private String name;

  @Override
  public String toString() {
    return name;
  }
}
