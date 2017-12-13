package com.xmartlabs.moviefan.ui.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

/**
 * Created by bruno on 12/11/17.
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class FilmResponse extends Film {
  @NonNull
  @SerializedName("genre_ids")
  private List<Long> genresIds;
}
