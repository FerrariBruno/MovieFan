package com.xmartlabs.moviefan.model;

import android.support.annotation.NonNull;

import com.xmartlabs.bigbang.core.model.EntityWithId;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * Created by bruno on 12/21/17.
 */
@Builder
@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class Genre implements EntityWithId<Long> {
  private Long id;
  @NonNull
  private String name;

  @Override
  public String toString() {
    return name;
  }
}
