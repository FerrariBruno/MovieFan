package com.xmartlabs.moviefan.ui.models;

import android.support.annotation.NonNull;

import lombok.Builder;
import lombok.Data;

/**
 * Created by bruno on 12/11/17.
 */
@Builder
@Data
public class Genre {
  @NonNull
  private long id;
  @NonNull
  private String name;
}
