package com.xmartlabs.moviefan.ui.models;

import android.graphics.Bitmap;

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
  @NonNull
  private Bitmap poster;
  @NonNull
  private String title;
  @NonNull
  private String duration;
  @NonNull
  private float popularity;
  @NonNull
  private String description;
  @NonNull
  private List<String> genres;
}
