package com.xmartlabs.moviefan.ui.models;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Builder;
import lombok.Data;

/**
 * Created by bruno on 12/8/17.
 */
@Builder
@Data
public class FilmResponse {
  @NonNull
  private int page;
  @NonNull
  @SerializedName("total_results")
  private int totalResults;
  @NonNull
  @SerializedName("total_pages")
  private int totalPages;
  @NonNull
  private List<Film> results;
}
