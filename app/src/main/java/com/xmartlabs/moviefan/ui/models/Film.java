package com.xmartlabs.moviefan.ui.models;

import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

/**
 * Created by bruno on 12/4/17.
 */
@Builder
@Data
public class Film {
  @Nullable
  @SerializedName("poster_path")
  private String poster;
  @NonNull
  private String title;
  @NonNull
  private float popularity;
  @NonNull
  private String overview;
  @NonNull
  @SerializedName("genre_ids")
  private List<String> genres;
}
