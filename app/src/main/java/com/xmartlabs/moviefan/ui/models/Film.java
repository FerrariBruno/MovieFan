package com.xmartlabs.moviefan.ui.models;

import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * Created by bruno on 12/4/17.
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Data
@NoArgsConstructor
public class Film {
  @Nullable
  private String posterPath;
  @NonNull
  private List<Genre> genres = new ArrayList<>();
  @NonNull
  private String overview;
  @NonNull
  private String title;

  private float popularity;
}
