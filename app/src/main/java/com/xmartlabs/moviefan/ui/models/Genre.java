package com.xmartlabs.moviefan.ui.models;

import android.support.annotation.NonNull;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * Created by bruno on 12/11/17.
 */
@Builder
@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class Genre {
  private long id;
  @NonNull
  private String name;

  @Override
  public String toString() {
    return name;
  }
}
