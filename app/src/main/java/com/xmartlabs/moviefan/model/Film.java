package com.xmartlabs.moviefan.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.xmartlabs.bigbang.core.model.EntityWithId;

import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by bruno on 12/21/17.
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Data
@NoArgsConstructor
public class Film implements EntityWithId<Long> {
  private Long id;
  private float popularity;

  @NonNull
  private List<Genre> genres;
  @Nullable
  private String overview;
  @Nullable
  private String posterPath;
  @NonNull
  private String title;
}
